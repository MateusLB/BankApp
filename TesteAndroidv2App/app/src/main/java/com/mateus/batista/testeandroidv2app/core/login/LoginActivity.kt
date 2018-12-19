package com.mateus.batista.testeandroidv2app.core.login

import android.os.Bundle
import androidx.lifecycle.Observer
import com.mateus.batista.testeandroidv2app.R
import com.mateus.batista.testeandroidv2app.base.view.BaseActivity
import com.mateus.batista.testeandroidv2app.utils.FieldStatus
import com.mateus.batista.testeandroidv2app.utils.beforeTextChanged
import com.mateus.batista.testeandroidv2app.utils.viewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = viewModel(viewModelFactory)
        setContentView(R.layout.activity_login)
        setUp()
        subscribeViewModel()
    }

    private fun setUp() {
        userInputEditText.beforeTextChanged { userInputLayout.error = null }
        passwordEditText.beforeTextChanged { passwordInputLayout.error = null }
        loginButton.setOnClickListener {
            loginViewModel.processSignIn(userInputEditText.text.toString(), passwordEditText.text.toString())
        }
    }

    private fun subscribeViewModel() {
        loginViewModel.getUserFieldStatus().observe(this, Observer { it -> it?.let { onUserError(it) } })
        loginViewModel.getPasswordFieldStatus().observe(this, Observer { it -> it?.let { onPassWordError(it) } })
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
}
