package com.mateus.batista.testeandroidv2app.core.bankPostings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mateus.batista.testeandroidv2app.base.viewModel.BaseViewModel
import com.mateus.batista.testeandroidv2app.data.local.entity.LocalStatement
import com.mateus.batista.testeandroidv2app.data.local.entity.UserAccountEntity
import com.mateus.batista.testeandroidv2app.data.mapper.StatementMapper
import com.mateus.batista.testeandroidv2app.data.remote.Response
import com.mateus.batista.testeandroidv2app.utils.CoroutinesContextProvider
import com.mateus.batista.testeandroidv2app.utils.FlowState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BankPostingsViewModel @Inject constructor(
    private val bankPostingsInteractor: BankPostingsInteractor,
    private val provider: CoroutinesContextProvider
) :
    BaseViewModel() {

    private val userAccountState by lazy { MutableLiveData<FlowState<UserAccountEntity>>() }
    private val statementsState by lazy { MutableLiveData<FlowState<List<LocalStatement>>>() }
    private var userId = 0

    fun getUserAccountState(): LiveData<FlowState<UserAccountEntity>> = userAccountState
    fun getStatementsState(): LiveData<FlowState<List<LocalStatement>>> = statementsState

    fun getUserAccount() {
        job = GlobalScope.launch(provider.iO) {
            val userAccount = bankPostingsInteractor.getUserAccount()
            withContext(provider.main) {
                userAccountState.value = FlowState(FlowState.Status.SUCCESS, data = userAccount)
                setUserId(userAccount.userId)
            }
        }
    }

    fun getStatements() {
        statementsState.value = FlowState(FlowState.Status.LOADING)
        job = GlobalScope.launch(provider.iO) {
            var response = bankPostingsInteractor.getStatements(userId)

            withContext(provider.main) {
                when (response) {
                    is Response.Success -> {
                        statementsState.value = FlowState(
                            FlowState.Status.SUCCESS,
                            data = StatementMapper.parseList(response.data.statementList)
                        )
                    }
                    is Response.Error -> {
                        statementsState.value = FlowState(FlowState.Status.ERROR, error = response.exception)
                    }
                }
            }
        }
    }

    private fun setUserId(userId: Int) {
        this.userId = userId
    }

    fun logout() {
        job = GlobalScope.launch(provider.iO) {
            bankPostingsInteractor.logout()
        }
    }
}