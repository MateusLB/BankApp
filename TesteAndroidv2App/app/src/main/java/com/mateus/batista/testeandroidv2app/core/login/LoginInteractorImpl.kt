package com.mateus.batista.testeandroidv2app.core.login

import com.mateus.batista.testeandroidv2app.data.local.entity.UserAccountEntity
import com.mateus.batista.testeandroidv2app.data.local.repository.LocalRepository
import com.mateus.batista.testeandroidv2app.data.remote.model.LoginBody
import com.mateus.batista.testeandroidv2app.data.remote.model.LoginResponse
import com.mateus.batista.testeandroidv2app.data.remote.Response
import com.mateus.batista.testeandroidv2app.data.remote.repository.RemoteRepository
import javax.inject.Inject

class LoginInteractorImpl @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : LoginInteractor {

    override fun getRecentLogin(): LoginBody? {
        return localRepository.getRecentLogin()
    }

    override fun setRecentLogin(loginBody: LoginBody) {
        localRepository.setRecentLogin(loginBody)
    }

    override suspend fun signIn(loginBody: LoginBody): Response<LoginResponse> {
        return remoteRepository.signIn(loginBody)
    }

    override fun saveUserAccount(userAccount: UserAccountEntity) {
        localRepository.saveUserAccount(userAccount)
    }
}