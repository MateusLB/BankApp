package com.mateus.batista.testeandroidv2app

import com.mateus.batista.testeandroidv2app.utils.CoroutinesContextProvider
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestContextProvider : CoroutinesContextProvider() {
    override val main: CoroutineContext = Dispatchers.Unconfined
    override val iO: CoroutineContext = Dispatchers.Unconfined
}