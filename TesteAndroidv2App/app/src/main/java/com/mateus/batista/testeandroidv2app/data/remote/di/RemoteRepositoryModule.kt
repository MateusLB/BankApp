package com.mateus.batista.testeandroidv2app.data.remote.di

import com.mateus.batista.testeandroidv2app.data.remote.BankService
import com.mateus.batista.testeandroidv2app.data.remote.NetworkStatus
import com.mateus.batista.testeandroidv2app.data.remote.repository.BankPostingsRemoteRepository
import com.mateus.batista.testeandroidv2app.data.remote.repository.LoginRemoteRepository
import com.mateus.batista.testeandroidv2app.data.remote.repository.RemoteRepositoryImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteRepositoryModule {

    @Singleton
    @Provides
    fun provideLoginRemoteRepository(networkStatus: NetworkStatus, bankService: BankService): LoginRemoteRepository =
        RemoteRepositoryImp(networkStatus, bankService)

    @Singleton
    @Provides
    fun provideBankPostingsRemoteRepository(networkStatus: NetworkStatus, bankService: BankService): BankPostingsRemoteRepository =
        RemoteRepositoryImp(networkStatus, bankService)
}