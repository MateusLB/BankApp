package com.mateus.batista.testeandroidv2app.data.local.di

import com.mateus.batista.testeandroidv2app.data.local.PreferencesManager
import com.mateus.batista.testeandroidv2app.data.local.dao.UserAccountDao
import com.mateus.batista.testeandroidv2app.data.local.repository.LocalRepository
import com.mateus.batista.testeandroidv2app.data.local.repository.LocalRepositoryImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalRepositoryModule {

    @Singleton
    @Provides
    fun provideLocalRepository(preferences: PreferencesManager, userAccountDao: UserAccountDao): LocalRepository =
        LocalRepositoryImp(preferences, userAccountDao)

}