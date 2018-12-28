package com.mateus.batista.testeandroidv2app.data.remote.model

import com.google.gson.annotations.SerializedName

data class LoginBody(
    @field:SerializedName("user")
    var user: String? = null,
    @field:SerializedName("password")
    var password: String? = null
)