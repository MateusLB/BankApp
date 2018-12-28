package com.mateus.batista.testeandroidv2app.data.remote

import com.mateus.batista.testeandroidv2app.data.remote.model.LoginBody
import com.mateus.batista.testeandroidv2app.data.remote.model.LoginResponse
import com.mateus.batista.testeandroidv2app.data.remote.model.StatementResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BankService {

    @POST("login")
    fun postLogin(@Body loginBody: LoginBody): Deferred<LoginResponse>

    @GET("statements/{id}")
    fun getStatements(@Path("id") userId: Int): Deferred<StatementResponse>
}