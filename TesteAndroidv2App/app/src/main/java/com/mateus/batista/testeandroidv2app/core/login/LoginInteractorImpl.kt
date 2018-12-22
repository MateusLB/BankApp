package com.mateus.batista.testeandroidv2app.core.login

import com.mateus.batista.testeandroidv2app.data.local.PreferencesManager
import com.mateus.batista.testeandroidv2app.data.remote.BankService
import com.mateus.batista.testeandroidv2app.data.remote.model.LoginBody
import com.mateus.batista.testeandroidv2app.data.remote.model.LoginResponse
import com.mateus.batista.testeandroidv2app.extensions.safeApiCall
import com.mateus.batista.testeandroidv2app.utils.Response
import javax.inject.Inject

class LoginInteractorImpl @Inject constructor(private val bankService: BankService,
                                              private val preferences : PreferencesManager) : LoginInteractor {
    override fun getRecentLogin(): LoginBody? {
      return preferences.getObject("", LoginBody::class.java)
    }

    override fun setRecentLogin(loginBody: LoginBody) {
        preferences.saveObject("",loginBody)
    }


    override suspend fun signIn(loginBody: LoginBody): Response<LoginResponse> {
        return safeApiCall({ true },
            { bankService.postLogin(loginBody).await() })
    }
}