package com.mateus.batista.testeandroidv2app.base.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mateus.batista.testeandroidv2app.app.BankApplication
import com.mateus.batista.testeandroidv2app.app.di.ApplicationComponent
import com.mateus.batista.testeandroidv2app.base.viewModel.ViewModelFactory
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as BankApplication).appComponet
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    fun callActivityNewTask(activity: Class<*>) {
        val intent = Intent()
        intent.setClass(this, activity)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}