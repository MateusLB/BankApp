package com.mateus.batista.testeandroidv2app.data.remote.model

import com.google.gson.annotations.SerializedName

data class StatementResponse(
    @field:SerializedName("statementList")
    var statementList: List<Statement>? = null,
    @field:SerializedName("error")
    var error: ErrorResponse? = null
)
