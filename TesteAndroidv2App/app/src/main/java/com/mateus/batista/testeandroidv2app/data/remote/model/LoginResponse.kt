package com.mateus.batista.testeandroidv2app.data.remote.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("userAccount")
    var userAccount: UserAccount? = null,
    @field:SerializedName("error")
    var error: ErrorResponse? = null
)