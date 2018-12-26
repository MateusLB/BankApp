package com.mateus.batista.testeandroidv2app.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_account")
data class UserAccountEntity(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    var userId: Int,
    var name: String,
    @ColumnInfo(name = "bank_account")
    var bankAccount: String,
    var agency: String,
    var balance: Double
)