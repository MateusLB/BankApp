package com.mateus.batista.testeandroidv2app.utils

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext


open class CoroutinesContextProvider {
    open val main: CoroutineContext by lazy { Dispatchers.Main }
    open val iO: CoroutineContext by lazy { Dispatchers.Default }
}