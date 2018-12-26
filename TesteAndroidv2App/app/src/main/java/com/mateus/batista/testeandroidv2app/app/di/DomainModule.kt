package com.mateus.batista.testeandroidv2app.app.di

import com.mateus.batista.testeandroidv2app.core.login.LoginInteractor
import com.mateus.batista.testeandroidv2app.core.login.LoginInteractorImpl
import com.mateus.batista.testeandroidv2app.data.local.PreferencesManager
import com.mateus.batista.testeandroidv2app.data.local.repository.LocalRepository
import com.mateus.batista.testeandroidv2app.data.remote.BankService
import com.mateus.batista.testeandroidv2app.data.remote.repository.RemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideLoginInteractor(localRepository: LocalRepository, remoteRepository: RemoteRepository) : LoginInteractor =
            LoginInteractorImpl(localRepository,remoteRepository)
}