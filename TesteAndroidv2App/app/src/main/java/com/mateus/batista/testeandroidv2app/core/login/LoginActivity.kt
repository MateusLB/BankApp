package com.mateus.batista.testeandroidv2app.core.login

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.mateus.batista.testeandroidv2app.R
import com.mateus.batista.testeandroidv2app.base.view.BaseActivity
import com.mateus.batista.testeandroidv2app.databinding.ActivityLoginBinding
import com.mateus.batista.testeandroidv2app.utils.viewModel

class LoginActivity : BaseActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = viewModel(viewModelFactory)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = loginViewModel
        binding.executePendingBindings()
    }
}
