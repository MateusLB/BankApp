package com.mateus.batista.testeandroidv2app.core.login

import android.os.Bundle
import com.mateus.batista.testeandroidv2app.R
import com.mateus.batista.testeandroidv2app.base.view.BaseActivity
import com.mateus.batista.testeandroidv2app.utils.viewModel

class LoginActivity : BaseActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = viewModel(viewModelFactory)
        setContentView(R.layout.activity_login)
    }
}
