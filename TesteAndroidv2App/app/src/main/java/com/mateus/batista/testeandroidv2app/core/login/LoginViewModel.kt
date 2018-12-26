package com.mateus.batista.testeandroidv2app.core.login

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mateus.batista.testeandroidv2app.app.Constants.UtilConstants.Companion.PASSWORD_REGEX
import com.mateus.batista.testeandroidv2app.base.viewModel.BaseViewModel
import com.mateus.batista.testeandroidv2app.data.mapper.UserAccountMapper
import com.mateus.batista.testeandroidv2app.data.remote.Response
import com.mateus.batista.testeandroidv2app.data.remote.model.LoginBody
import com.mateus.batista.testeandroidv2app.data.remote.model.LoginResponse
import com.mateus.batista.testeandroidv2app.utils.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginInteractor: LoginInteractor,
    private val provider: CoroutinesContextProvider
) : BaseViewModel() {

    private val userFieldStatus by lazy { MutableLiveData<FieldStatus>() }
    private val passwordFieldStatus by lazy { MutableLiveData<FieldStatus>() }
    private val signInStatus by lazy { MutableLiveData<FlowState<LoginResponse>>() }
    private val recentLoginData by lazy { MutableLiveData<LoginBody>() }

    fun getUserFieldStatus(): LiveData<FieldStatus> = userFieldStatus
    fun getPasswordFieldStatus(): LiveData<FieldStatus> = passwordFieldStatus
    fun getSignInStatus(): LiveData<FlowState<LoginResponse>> = signInStatus
    fun getRecentLoginData(): LiveData<LoginBody> = recentLoginData

    fun getRecentLogin() {
        recentLoginData.value = loginInteractor.getRecentLogin()
    }

    fun processSignIn(loginBody: LoginBody) {
        if (isUserValid(loginBody.user!!) && isPasswordValid(loginBody.password!!)) {
            signInStatus.value = FlowState(FlowState.Status.LOADING)

            job = GlobalScope.launch(provider.iO) {
                var response = loginInteractor.signIn(loginBody)

                withContext(provider.main) {
                    when (response) {
                        is Response.Success -> {
                            saveRecentLogin(loginBody)
                            saveUserDetail(response.data)
                            signInStatus.value = FlowState(FlowState.Status.SUCCESS)
                        }
                        is Response.Error -> {
                            signInStatus.value = FlowState(FlowState.Status.ERROR, error = response.exception)
                        }
                    }
                }
            }
        }
    }

    private fun saveUserDetail(loginResponse: LoginResponse) {
        loginResponse.userAccount?.let { userAccount ->
            loginInteractor.saveUserAccount(UserAccountMapper.parse(userAccount))
        }
    }

    fun isUserValid(user: String): Boolean = when {
        user.isEmpty() -> {
            userFieldStatus.value = FieldStatus.EMPTY
            false
        }
        !isEmailValid(user) && !isCPFValid(user) -> {
            userFieldStatus.value = FieldStatus.INVALID
            false
        }
        else -> {
            userFieldStatus.value = FieldStatus.VALID
            true
        }
    }

    private fun isEmailValid(email: String): Boolean =
        PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()

    private fun isCPFValid(cpf: String): Boolean = CPFUtil.myValidateCPF(cpf)

    fun isPasswordValid(password: String): Boolean = when {
        password.isEmpty() -> {
            passwordFieldStatus.value = FieldStatus.EMPTY
            false
        }
        !(password.contains(Regex(PASSWORD_REGEX))) -> {
            passwordFieldStatus.value = FieldStatus.INVALID
            false
        }
        else -> {
            passwordFieldStatus.value = FieldStatus.VALID
            true
        }
    }

    private fun saveRecentLogin(loginBody: LoginBody) {
        loginInteractor.setRecentLogin(loginBody)
    }
}