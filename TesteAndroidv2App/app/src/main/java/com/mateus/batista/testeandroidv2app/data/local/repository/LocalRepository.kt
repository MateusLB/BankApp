package com.mateus.batista.testeandroidv2app.data.local.repository

import com.mateus.batista.testeandroidv2app.data.local.entity.UserAccountEntity
import com.mateus.batista.testeandroidv2app.data.remote.model.LoginBody

interface LocalRepository {
    fun getRecentLogin(): LoginBody?
    fun setRecentLogin(loginBody: LoginBody)
    fun saveUserAccount(userAccount: UserAccountEntity)
}