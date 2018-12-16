package com.mateus.batista.testeandroidv2app.data.remote.model

import com.google.gson.annotations.SerializedName

class StatementResponse {
    @SerializedName("statementList")
    var statementList: List<Statement>? = null
    @SerializedName("error")
    var error: ErrorResponse? = null
        private set

    fun setError(ErrorResponse: Error) {
        this.error = error
    }

}
