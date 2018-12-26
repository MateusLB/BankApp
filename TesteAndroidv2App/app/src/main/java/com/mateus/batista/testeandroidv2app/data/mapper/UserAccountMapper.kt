package com.mateus.batista.testeandroidv2app.data.mapper

import com.mateus.batista.testeandroidv2app.data.local.entity.UserAccountEntity
import com.mateus.batista.testeandroidv2app.data.remote.model.UserAccount

object UserAccountMapper {

    fun parse(userAccount: UserAccount): UserAccountEntity =
        UserAccountEntity(
            userId = userAccount.userId,
            name = userAccount.name ?: "",
            bankAccount = userAccount.bankAccount ?: "",
            agency = userAccount.agency ?: "",
            balance = userAccount.balance
        )

}