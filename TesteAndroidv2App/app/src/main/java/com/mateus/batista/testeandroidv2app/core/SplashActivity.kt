package com.mateus.batista.testeandroidv2app.core

import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.mateus.batista.testeandroidv2app.R
import com.mateus.batista.testeandroidv2app.base.view.BaseActivity
import com.mateus.batista.testeandroidv2app.core.login.LoginActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            callActivityNewTask(LoginActivity::class.java)
        },500)
    }
}
