package com.mateus.batista.testeandroidv2app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mateus.batista.testeandroidv2app.data.local.dao.UserAccountDao
import com.mateus.batista.testeandroidv2app.data.local.entity.UserAccountEntity

@Database(
    entities = [UserAccountEntity::class],
    version = 2,
    exportSchema = false
)
abstract class BankDataBase : RoomDatabase() {

    abstract fun UserAccountDao(): UserAccountDao
}