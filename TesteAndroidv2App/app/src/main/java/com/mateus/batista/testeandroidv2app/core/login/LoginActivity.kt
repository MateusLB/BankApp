package com.mateus.batista.testeandroidv2app.core.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.mateus.batista.testeandroidv2app.R
import com.mateus.batista.testeandroidv2app.base.view.BaseActivity
import com.mateus.batista.testeandroidv2app.core.bankPostings.BankPostingsActivity
import com.mateus.batista.testeandroidv2app.data.remote.model.LoginBody
import com.mateus.batista.testeandroidv2app.data.remote.model.LoginResponse
import com.mateus.batista.testeandroidv2app.utils.FieldStatus
import com.mateus.batista.testeandroidv2app.extensions.beforeTextChanged
import com.mateus.batista.testeandroidv2app.extensions.viewModel
import com.mateus.batista.testeandroidv2app.utils.FlowState
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.partial_progress_bar.*
import org.jetbrains.anko.startActivity

class LoginActivity : BaseActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = viewModel(viewModelFactory)
        setContentView(R.layout.activity_login)
        setUp()
        subscribeViewModel()
        loginViewModel.getRecentLogin()
    }

    private fun setUp() {
        userInputEditText.beforeTextChanged { userInputLayout.error = null }
        passwordEditText.beforeTextChanged { passwordInputLayout.error = null }
        loginButton.setOnClickListener {
            loginViewModel.processSignIn(userInputEditText.text.toString(), passwordEditText.text.toString())
        }
    }

    private fun subscribeViewModel() {
        loginViewModel.getRecentLoginData().observe(this, Observer { it -> it?.let { setRecentLogin(it) } })
        loginViewModel.getUserFieldStatus().observe(this, Observer { it -> it?.let { onUserError(it) } })
        loginViewModel.getPasswordFieldStatus().observe(this, Observer { it -> it?.let { onPassWordError(it) } })
        loginViewModel.getSignInStatus().observe(this, Observer { it -> it?.let { handleSignInStatus(it) } })
    }

    private fun setRecentLogin(recentLogin: LoginBody) {
        userInputEditText.setText(recentLogin.user)
        passwordEditText.setText(recentLogin.password)
    }

    private fun onUserError(status: FieldStatus) = when (status) {
        FieldStatus.EMPTY -> userInputLayout.error = resources.getString(R.string.required_field_error)
        FieldStatus.INVALID -> userInputLayout.error = resources.getString(R.string.invalid_format_user_error)
        FieldStatus.VALID -> userInputLayout.error = null
    }

    private fun onPassWordError(status: FieldStatus) = when (status) {
        FieldStatus.EMPTY -> passwordInputLayout.error = resources.getString(R.string.required_field_error)
        FieldStatus.INVALID -> passwordInputLayout.error = resources.getString(R.string.invalid_format_password_error)
        FieldStatus.VALID -> passwordInputLayout.error = null
    }

    private fun handleSignInStatus(state: FlowState<LoginResponse>) {
        when (state.status) {
            FlowState.Status.LOADING -> {
                progressBar.visibility = View.VISIBLE
                loginButton.isEnabled = false

            }
            FlowState.Status.SUCCESS -> {
                progressBar.visibility = View.GONE
                loginButton.isEnabled = true
                startActivity<BankPostingsActivity>()
            }
            FlowState.Status.ERROR -> {
                progressBar.visibility = View.GONE
                loginButton.isEnabled = true

            }
        }
    }
}
