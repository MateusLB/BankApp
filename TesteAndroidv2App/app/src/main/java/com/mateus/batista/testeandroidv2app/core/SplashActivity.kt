package com.mateus.batista.testeandroidv2app.core

import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.mateus.batista.testeandroidv2app.R
import com.mateus.batista.testeandroidv2app.app.Constants.SharedPreferences.Companion.IS_LOGGED
import com.mateus.batista.testeandroidv2app.base.view.BaseActivity
import com.mateus.batista.testeandroidv2app.core.bankPostings.BankPostingsActivity
import com.mateus.batista.testeandroidv2app.core.login.LoginActivity
import com.mateus.batista.testeandroidv2app.data.local.PreferencesManager

class SplashActivity : BaseActivity() {

    private val preferences by lazy { PreferencesManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            if (preferences.getBoolean(IS_LOGGED,false)) {
                callActivityNewTask(BankPostingsActivity::class.java)
            } else {
                callActivityNewTask(LoginActivity::class.java)
            }
        }, 500)
    }
}
