package com.mateus.batista.testeandroidv2app.core.login

import com.mateus.batista.testeandroidv2app.data.remote.model.LoginBody
import com.mateus.batista.testeandroidv2app.data.remote.model.LoginResponse
import com.mateus.batista.testeandroidv2app.utils.Response

interface LoginInteractor {

    suspend fun signIn(loginBody: LoginBody) : Response<LoginResponse>
    fun getRecentLogin() : LoginBody?
    fun setRecentLogin(loginBody: LoginBody)
}