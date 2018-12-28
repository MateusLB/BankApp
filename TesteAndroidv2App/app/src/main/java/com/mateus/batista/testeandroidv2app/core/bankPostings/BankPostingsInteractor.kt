package com.mateus.batista.testeandroidv2app.core.bankPostings

import com.mateus.batista.testeandroidv2app.data.local.entity.UserAccountEntity
import com.mateus.batista.testeandroidv2app.data.remote.Response
import com.mateus.batista.testeandroidv2app.data.remote.model.StatementResponse

interface BankPostingsInteractor {
    suspend fun getStatements(userId: Int): Response<StatementResponse>
    fun getUserAccount(): UserAccountEntity
    fun logout()
}