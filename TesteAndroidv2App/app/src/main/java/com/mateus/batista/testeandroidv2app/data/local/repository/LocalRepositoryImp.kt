package com.mateus.batista.testeandroidv2app.data.local.repository

import com.mateus.batista.testeandroidv2app.app.Constants
import com.mateus.batista.testeandroidv2app.data.local.PreferencesManager
import com.mateus.batista.testeandroidv2app.data.local.dao.UserAccountDao
import com.mateus.batista.testeandroidv2app.data.local.entity.UserAccountEntity
import com.mateus.batista.testeandroidv2app.data.remote.model.LoginBody
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class LocalRepositoryImp @Inject constructor(
    private val preferences: PreferencesManager,
    private val userAccountDao: UserAccountDao
) : LocalRepository {

    override fun getRecentLogin(): LoginBody? {
        return preferences.getObject(Constants.SharedPreferences.RECENT_LOGIN, LoginBody::class.java)
    }

    override fun setRecentLogin(loginBody: LoginBody) {
        preferences.saveObject(Constants.SharedPreferences.RECENT_LOGIN, loginBody)
    }

    override fun saveUserAccount(userAccount: UserAccountEntity) {
        doAsync {
            userAccountDao.insert(userAccount)
        }
    }
}