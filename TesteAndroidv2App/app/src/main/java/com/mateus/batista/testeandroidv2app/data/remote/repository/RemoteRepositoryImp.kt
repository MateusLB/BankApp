package com.mateus.batista.testeandroidv2app.data.remote.repository

import com.mateus.batista.testeandroidv2app.data.remote.BankService
import com.mateus.batista.testeandroidv2app.data.remote.NetworkStatus
import com.mateus.batista.testeandroidv2app.data.remote.Response
import com.mateus.batista.testeandroidv2app.data.remote.model.LoginBody
import com.mateus.batista.testeandroidv2app.data.remote.model.LoginResponse
import com.mateus.batista.testeandroidv2app.data.remote.model.StatementResponse
import com.mateus.batista.testeandroidv2app.extensions.safeApiCall
import javax.inject.Inject

class RemoteRepositoryImp @Inject constructor(
    private val networkStatus: NetworkStatus,
    private val bankService: BankService
) : LoginRemoteRepository, BankPostingsRemoteRepository {
    override suspend fun getStatements(userId: Int): Response<StatementResponse> {
        return safeApiCall({ true },
            {bankService.getStatements(userId).await()})
    }

    override suspend fun signIn(loginBody: LoginBody): Response<LoginResponse> {
        return safeApiCall({ true },
            { bankService.postLogin(loginBody).await() })
    }
}