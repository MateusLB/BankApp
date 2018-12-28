package com.mateus.batista.testeandroidv2app.core.bankPostings

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.mateus.batista.testeandroidv2app.R
import com.mateus.batista.testeandroidv2app.base.view.BaseActivity
import com.mateus.batista.testeandroidv2app.base.view.listeners.OnItemClickListener
import com.mateus.batista.testeandroidv2app.core.bankPostings.adapter.StatementListAdapter
import com.mateus.batista.testeandroidv2app.core.login.LoginActivity
import com.mateus.batista.testeandroidv2app.data.local.entity.LocalStatement
import com.mateus.batista.testeandroidv2app.data.local.entity.UserAccountEntity
import com.mateus.batista.testeandroidv2app.extensions.viewModel
import com.mateus.batista.testeandroidv2app.utils.FlowState
import kotlinx.android.synthetic.main.activity_bank_postings.*
import kotlinx.android.synthetic.main.partial_progress_bar.*

class BankPostingsActivity : BaseActivity(), OnItemClickListener<LocalStatement> {


    private lateinit var bankPostingsViewModel: BankPostingsViewModel
    private lateinit var statementListAdapter: StatementListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bankPostingsViewModel = viewModel(viewModelFactory)
        setContentView(R.layout.activity_bank_postings)
        setUp()
        subscribeViewModel()
        bankPostingsViewModel.getUserAccount()
        bankPostingsViewModel.getStatements()
    }

    private fun setUp() {
        logout.setOnClickListener {
            bankPostingsViewModel.logout()
            callActivityNewTask(LoginActivity::class.java)
        }
    }

    private fun subscribeViewModel() {
        bankPostingsViewModel.getStatementsState()
            .observe(this, Observer { it -> it?.let { handleStatementsState(it) } })
        bankPostingsViewModel.getUserAccountState()
            .observe(this, Observer { it -> it?.let { handleUserAccountState(it) } })
    }

    private fun handleUserAccountState(state: FlowState<UserAccountEntity>) {
        when (state.status) {
            FlowState.Status.SUCCESS -> state.data?.let {
                name.text = it.name
                balance.text = it.balance
                bankAccount.text = it.bankAccount
            }
            else -> {
            }
        }
    }

    private fun handleStatementsState(state: FlowState<List<LocalStatement>>) {
        when (state.status) {
            FlowState.Status.LOADING -> {
                progressBar.visibility = View.VISIBLE

            }
            FlowState.Status.SUCCESS -> state.data?.let {
                progressBar.visibility = View.GONE
                statementListAdapter = StatementListAdapter(it, this)
                statementsRecycle.adapter = statementListAdapter
                statementListAdapter.notifyDataSetChanged()
            }
            FlowState.Status.ERROR -> {
                progressBar.visibility = View.GONE
                state.error?.let { handleErrors(it) }
            }
        }
    }


    override fun onItemClick(item: LocalStatement, position: Int) {
        //Demonstrar como é implementado o onItemClick
    }
}
