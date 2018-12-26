package com.mateus.batista.testeandroidv2app.data.remote.repository

import com.mateus.batista.testeandroidv2app.data.remote.Response
import com.mateus.batista.testeandroidv2app.data.remote.model.LoginBody
import com.mateus.batista.testeandroidv2app.data.remote.model.LoginResponse

interface RemoteRepository {
    suspend fun signIn(loginBody: LoginBody): Response<LoginResponse>
}