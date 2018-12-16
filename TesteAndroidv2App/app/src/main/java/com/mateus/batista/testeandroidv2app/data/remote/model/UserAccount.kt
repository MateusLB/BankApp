package com.mateus.batista.testeandroidv2app.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserAccount(
    @field:SerializedName("userId")
    var userId: Int = 0,
    @field:SerializedName("name")
    var name: String? = null,
    @field:SerializedName("bankAccount")
    var bankAccount: String? = null,
    @field:SerializedName("agency")
    var agency: String? = null,
    @field:SerializedName("balance")
    var balance: Double = 0.0
)
