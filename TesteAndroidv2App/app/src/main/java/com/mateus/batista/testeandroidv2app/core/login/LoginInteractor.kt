package com.mateus.batista.testeandroidv2app.core.login

import com.mateus.batista.testeandroidv2app.data.local.entity.UserAccountEntity
import com.mateus.batista.testeandroidv2app.data.remote.model.LoginBody
import com.mateus.batista.testeandroidv2app.data.remote.model.LoginResponse
import com.mateus.batista.testeandroidv2app.data.remote.Response

interface LoginInteractor {

    suspend fun signIn(loginBody: LoginBody): Response<LoginResponse>
    fun getRecentLogin(): LoginBody?
    fun setRecentLogin(loginBody: LoginBody)
    fun saveUserAccount(userAccount: UserAccountEntity)
}