package com.mateus.batista.testeandroidv2app.base.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mateus.batista.testeandroidv2app.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun callActivityNewTask(activity: Class<*>) {
        val intent = Intent()
        intent.setClass(this, activity)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}