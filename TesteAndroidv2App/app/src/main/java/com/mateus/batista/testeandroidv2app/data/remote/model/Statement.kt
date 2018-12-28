package com.mateus.batista.testeandroidv2app.data.remote.model

import com.google.gson.annotations.SerializedName

data class Statement(
    @field:SerializedName("title")
    var title: String? = null,
    @field:SerializedName("desc")
    var desc: String? = null,
    @field:SerializedName("date")
    var date: String? = null,
    @field:SerializedName("value")
    var value: Double = 0.0
)
