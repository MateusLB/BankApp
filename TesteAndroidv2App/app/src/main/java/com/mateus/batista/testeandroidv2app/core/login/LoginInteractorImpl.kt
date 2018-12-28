package com.mateus.batista.testeandroidv2app.core.login

import com.mateus.batista.testeandroidv2app.data.local.entity.UserAccountEntity
import com.mateus.batista.testeandroidv2app.data.local.repository.LoginLocalRepository
import com.mateus.batista.testeandroidv2app.data.remote.Response
import com.mateus.batista.testeandroidv2app.data.remote.model.LoginBody
import com.mateus.batista.testeandroidv2app.data.remote.model.LoginResponse
import com.mateus.batista.testeandroidv2app.data.remote.repository.LoginRemoteRepository
import javax.inject.Inject

class LoginInteractorImpl @Inject constructor(
    private val loginLocalRepository: LoginLocalRepository,
    private val loginRemoteRepository: LoginRemoteRepository
) : LoginInteractor {

    override fun getRecentLogin(): LoginBody? {
        return loginLocalRepository.getRecentLogin()
    }

    override fun setRecentLogin(loginBody: LoginBody) {
        loginLocalRepository.setRecentLogin(loginBody)
    }

    override suspend fun signIn(loginBody: LoginBody): Response<LoginResponse> {
        return loginRemoteRepository.signIn(loginBody)
    }

    override fun saveUserAccount(userAccount: UserAccountEntity) {
        loginLocalRepository.saveUserAccount(userAccount)
    }

    override fun setIsLogged() {
        loginLocalRepository.setIsLogged()
    }
}