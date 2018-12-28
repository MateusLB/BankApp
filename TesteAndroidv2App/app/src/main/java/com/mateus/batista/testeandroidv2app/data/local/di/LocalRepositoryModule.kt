package com.mateus.batista.testeandroidv2app.data.local.di

import com.mateus.batista.testeandroidv2app.data.local.PreferencesManager
import com.mateus.batista.testeandroidv2app.data.local.dao.UserAccountDao
import com.mateus.batista.testeandroidv2app.data.local.repository.BankPostingsLocalRepository
import com.mateus.batista.testeandroidv2app.data.local.repository.LoginLocalRepository
import com.mateus.batista.testeandroidv2app.data.local.repository.LocalRepositoryImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalRepositoryModule {

    @Singleton
    @Provides
    fun provideLoginLocalRepository(preferences: PreferencesManager, userAccountDao: UserAccountDao): LoginLocalRepository =
        LocalRepositoryImp(preferences, userAccountDao)

    @Singleton
    @Provides
    fun provideBankPostingsLocalRepository(preferences: PreferencesManager, userAccountDao: UserAccountDao): BankPostingsLocalRepository =
        LocalRepositoryImp(preferences, userAccountDao)

}