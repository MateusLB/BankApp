package com.mateus.batista.testeandroidv2app.app.di

import com.mateus.batista.testeandroidv2app.core.bankPostings.BankPostingsInteractor
import com.mateus.batista.testeandroidv2app.core.bankPostings.BankPostingsInteractorImp
import com.mateus.batista.testeandroidv2app.core.login.LoginInteractor
import com.mateus.batista.testeandroidv2app.core.login.LoginInteractorImpl
import com.mateus.batista.testeandroidv2app.data.local.repository.BankPostingsLocalRepository
import com.mateus.batista.testeandroidv2app.data.local.repository.LoginLocalRepository
import com.mateus.batista.testeandroidv2app.data.remote.repository.BankPostingsRemoteRepository
import com.mateus.batista.testeandroidv2app.data.remote.repository.LoginRemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideLoginInteractor(loginLocalRepository: LoginLocalRepository,
                               loginRemoteRepository: LoginRemoteRepository) : LoginInteractor =
            LoginInteractorImpl(loginLocalRepository,loginRemoteRepository)

    @Singleton
    @Provides
    fun provideBankPostingsInteractor(bankPostingsLocalRepository: BankPostingsLocalRepository,
                                      bankPostingsRemoteRepository: BankPostingsRemoteRepository)
            : BankPostingsInteractor = BankPostingsInteractorImp(bankPostingsLocalRepository, bankPostingsRemoteRepository)

}