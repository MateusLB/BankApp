package com.mateus.batista.testeandroidv2app.data.mapper

import com.mateus.batista.testeandroidv2app.data.local.entity.UserAccountEntity
import com.mateus.batista.testeandroidv2app.data.remote.model.UserAccount
import com.mateus.batista.testeandroidv2app.utils.ConvertUtil.Companion.getBankAccountForm
import com.mateus.batista.testeandroidv2app.utils.ConvertUtil.Companion.getRealForm

object UserAccountMapper {

    fun parse(userAccount: UserAccount): UserAccountEntity =
        UserAccountEntity(
            userId = userAccount.userId,
            name = userAccount.name ?: "",
            bankAccount = getBankAccountForm("${userAccount.agency}${userAccount.bankAccount}"),
            balance = getRealForm(userAccount.balance)
        )
}