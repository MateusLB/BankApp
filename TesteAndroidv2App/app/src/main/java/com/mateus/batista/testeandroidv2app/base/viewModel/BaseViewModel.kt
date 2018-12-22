package com.mateus.batista.testeandroidv2app.base.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

open class BaseViewModel : ViewModel() {

    protected var job : Job? = null

    override fun onCleared() {
        job?.cancel()
        super.onCleared()
    }
}