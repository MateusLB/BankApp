package com.mateus.batista.testeandroidv2app.app

import android.app.Application
import com.mateus.batista.testeandroidv2app.app.di.ApplicationComponent
import com.mateus.batista.testeandroidv2app.app.di.DaggerApplicationComponent

class BankApplication : Application() {

    val appComponet: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        injectMembers()
    }

    private fun injectMembers() = appComponet.inject(this)
}