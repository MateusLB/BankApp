package com.mateus.batista.testeandroidv2app.data.local.di

import android.content.Context
import com.mateus.batista.testeandroidv2app.data.local.PreferencesManager
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class PreferencesManagerModule {

    @Provides
    @Singleton
    fun providePreferencesManager(@Named("ApplicationContext") context: Context) =
        PreferencesManager(context)
}