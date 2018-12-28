package com.mateus.batista.testeandroidv2app.login

import com.mateus.batista.testeandroidv2app.data.local.entity.UserAccountEntity
import com.mateus.batista.testeandroidv2app.data.remote.Response
import com.mateus.batista.testeandroidv2app.data.remote.model.ErrorResponse
import com.mateus.batista.testeandroidv2app.data.remote.model.LoginBody
import com.mateus.batista.testeandroidv2app.data.remote.model.LoginResponse
import com.mateus.batista.testeandroidv2app.data.remote.model.UserAccount

object LoginFactory {

    fun createValidLoginBody(): LoginBody = LoginBody("myemail@bank.com", "A1q@")
    fun createInvalidLoginBody(): LoginBody = LoginBody("myemailbank", "a1q@")

    fun createSuccessLoginResponse(): Response.Success<LoginResponse> = Response.Success(
        LoginResponse(
            UserAccount(
                userId = 1,
                name = "bankName",
                bankAccount = "bankAccount",
                agency = "agency",
                balance = 500.0
            ),
            ErrorResponse()
        )
    )

    fun createErrorLoginResponse(): Response.Error = Response.Error(Throwable())

    fun createUserAccountEntity(): UserAccountEntity = UserAccountEntity(
        userId = 1,
        name = "bankName",
        bankAccount = "bankAccount",
        balance = "R$500"
    )

}