package com.mateus.batista.testeandroidv2app.data.local.di

import android.content.Context
import androidx.room.Room
import com.mateus.batista.testeandroidv2app.app.Constants.Database.Companion.DATABASE_NAME
import com.mateus.batista.testeandroidv2app.data.local.BankDataBase
import com.mateus.batista.testeandroidv2app.data.local.dao.UserAccountDao
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class BankDataBaseModule {

    @Provides
    @Singleton
    fun provideUserAccountDao(db: BankDataBase) : UserAccountDao = db.UserAccountDao()

    @Provides
    @Singleton
    fun provideBankDataBase(@Named("ApplicationContext") context: Context) : BankDataBase =
            Room.databaseBuilder(context,BankDataBase::class.java,DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
}