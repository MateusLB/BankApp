package com.mateus.batista.testeandroidv2app.data.remote.repository

import com.mateus.batista.testeandroidv2app.data.remote.Response
import com.mateus.batista.testeandroidv2app.data.remote.model.StatementResponse

interface BankPostingsRemoteRepository {
    suspend fun getStatements(userId : Int) : Response<StatementResponse>
}