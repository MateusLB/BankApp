package com.mateus.batista.testeandroidv2app.base.data

import androidx.room.*

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj : T) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg obj : T)

    @Update
    fun update(obj : T)

    @Delete
    fun delete(obj : T)
}