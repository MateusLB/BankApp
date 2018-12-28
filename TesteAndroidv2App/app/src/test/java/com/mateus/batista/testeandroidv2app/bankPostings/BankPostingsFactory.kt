package com.mateus.batista.testeandroidv2app.bankPostings

import com.mateus.batista.testeandroidv2app.data.local.entity.UserAccountEntity
import com.mateus.batista.testeandroidv2app.data.remote.Response
import com.mateus.batista.testeandroidv2app.data.remote.model.ErrorResponse
import com.mateus.batista.testeandroidv2app.data.remote.model.StatementResponse

object BankPostingsFactory {

    fun createUserAccountEntity(): UserAccountEntity = UserAccountEntity(
        userId = 1,
        name = "bankName",
        bankAccount = "bankAccount",
        balance = "R$500"
    )

    fun createSuccessStatementsResponse(): Response.Success<StatementResponse> =
        Response.Success(
            StatementResponse(
                listOf(),
                ErrorResponse()
            )
        )

    fun createErrorStatementsResponse(): Response.Error = Response.Error(Throwable())
}