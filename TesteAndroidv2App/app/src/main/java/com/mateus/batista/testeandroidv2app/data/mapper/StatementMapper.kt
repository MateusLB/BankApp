package com.mateus.batista.testeandroidv2app.data.mapper

import android.annotation.SuppressLint
import com.mateus.batista.testeandroidv2app.data.local.entity.LocalStatement
import com.mateus.batista.testeandroidv2app.data.remote.model.Statement
import com.mateus.batista.testeandroidv2app.utils.ConvertUtil.Companion.getDataFormat
import com.mateus.batista.testeandroidv2app.utils.ConvertUtil.Companion.getRealForm
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

object StatementMapper {


    fun parseList(statements: List<Statement>?): List<LocalStatement> {
        return statements?.map { it -> parse(it) } ?: listOf()
    }

    fun parse(statement: Statement): LocalStatement =
        LocalStatement(
            title = statement.title ?: "",
            desc = statement.desc ?: "",
            date = getDataFormat(statement.date),
            value = getRealForm(statement.value)
        )



}