package com.mateus.batista.testeandroidv2app.utils

class FlowState<D>(
    val status: Status,
    val data: D? = null,
    val error: Throwable? = null
) {
    enum class Status {
        LOADING, SUCCESS, ERROR
    }
}