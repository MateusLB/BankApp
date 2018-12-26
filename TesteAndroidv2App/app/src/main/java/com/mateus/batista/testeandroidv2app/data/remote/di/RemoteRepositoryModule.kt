package com.mateus.batista.testeandroidv2app.data.remote.di

import com.mateus.batista.testeandroidv2app.data.remote.BankService
import com.mateus.batista.testeandroidv2app.data.remote.NetworkStatus
import com.mateus.batista.testeandroidv2app.data.remote.repository.RemoteRepository
import com.mateus.batista.testeandroidv2app.data.remote.repository.RemoteRepositoryImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteRepositoryModule {

    @Singleton
    @Provides
    fun provideRemoteRepository(networkStatus: NetworkStatus, bankService: BankService): RemoteRepository =
        RemoteRepositoryImp(networkStatus, bankService)
}