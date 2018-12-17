package com.mateus.batista.testeandroidv2app.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity

inline fun <reified T : ViewModel> AppCompatActivity.viewModel(factory: ViewModelProvider.Factory): T
        = ViewModelProviders.of(this, factory)[T::class.java]