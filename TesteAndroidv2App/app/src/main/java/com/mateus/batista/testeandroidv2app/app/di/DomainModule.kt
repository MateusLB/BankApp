package com.mateus.batista.testeandroidv2app.app.di

import com.mateus.batista.testeandroidv2app.core.login.LoginInteractor
import com.mateus.batista.testeandroidv2app.core.login.LoginInteractorImpl
import com.mateus.batista.testeandroidv2app.data.local.PreferencesManager
import com.mateus.batista.testeandroidv2app.data.remote.BankService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideLoginInteractor(bankService : BankService, preferencesManager: PreferencesManager) : LoginInteractor =
            LoginInteractorImpl(bankService,preferencesManager)
}