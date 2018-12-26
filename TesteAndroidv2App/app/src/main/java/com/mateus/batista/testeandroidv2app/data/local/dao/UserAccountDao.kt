package com.mateus.batista.testeandroidv2app.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.mateus.batista.testeandroidv2app.base.data.BaseDao
import com.mateus.batista.testeandroidv2app.data.local.entity.UserAccountEntity

@Dao
abstract class UserAccountDao : BaseDao<UserAccountEntity> {

    @Query("SELECT * FROM user_account")
    abstract fun getUserAccount(): UserAccountEntity

    @Query("DELETE FROM user_account")
    abstract fun deleteData()
}