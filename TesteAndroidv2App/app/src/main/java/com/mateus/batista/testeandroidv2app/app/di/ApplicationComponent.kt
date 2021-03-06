package com.mateus.batista.testeandroidv2app.app.di

import com.mateus.batista.testeandroidv2app.app.BankApplication
import com.mateus.batista.testeandroidv2app.base.view.BaseActivity
import com.mateus.batista.testeandroidv2app.base.viewModel.di.ViewModelModule
import com.mateus.batista.testeandroidv2app.data.local.di.BankDataBaseModule
import com.mateus.batista.testeandroidv2app.data.local.di.LocalRepositoryModule
import com.mateus.batista.testeandroidv2app.data.local.di.PreferencesManagerModule
import com.mateus.batista.testeandroidv2app.data.remote.di.RemoteRepositoryModule
import com.mateus.batista.testeandroidv2app.data.remote.di.ServiceModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        PreferencesManagerModule::class,
        ServiceModule::class,
        ViewModelModule::class,
        DomainModule::class,
        BankDataBaseModule::class,
        LocalRepositoryModule::class,
        RemoteRepositoryModule::class
    ]
)
interface ApplicationComponent {

    fun inject(application: BankApplication)
    fun inject(activity: BaseActivity)
}