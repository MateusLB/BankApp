package com.mateus.batista.testeandroidv2app.base.viewModel.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mateus.batista.testeandroidv2app.base.viewModel.ViewModelFactory
import com.mateus.batista.testeandroidv2app.core.bankPostings.BankPostingsViewModel
import com.mateus.batista.testeandroidv2app.core.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindsLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BankPostingsViewModel::class)
    abstract fun bindsBankPostingsViewModel(bankPostingsViewModel: BankPostingsViewModel): ViewModel



}