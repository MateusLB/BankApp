package com.mateus.batista.testeandroidv2app.data.local

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import com.google.gson.Gson
import com.mateus.batista.testeandroidv2app.app.Constants.SharedPreferences.Companion.PREF_NAME

class PreferencesManager(context: Context) {

    private val mPref: SharedPreferences?

    init {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun setValue(key: String, value: Long) {
        mPref!!.edit()
            .putLong(encrypt(key), value)
            .apply()
    }

    fun setValue(key: String, value: Int) {
        mPref!!.edit()
            .putInt(encrypt(key), value)
            .apply()
    }

    fun setValue(key: String, value: Float) {
        mPref!!.edit()
            .putFloat(encrypt(key), value)
            .apply()
    }

    fun setValue(key: String, value: String) {
        mPref!!.edit()
            .putString(encrypt(key), encrypt(value))
            .apply()
    }

    fun setValue(key: String, value: Boolean) {
        mPref!!.edit()
            .putBoolean(encrypt(key), value)
            .apply()
    }

    fun getLong(key: String): Long {
        return mPref!!.getLong(encrypt(key), 0)
    }


    fun getInt(key: String): Int {
        return mPref!!.getInt(encrypt(key), 0)
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return mPref!!.getInt(encrypt(key), defaultValue)
    }

    fun getFloat(key: String): Float {
        return mPref!!.getFloat(encrypt(key), 0f)
    }


    fun getString(key: String): String {
        val string = mPref!!.getString(encrypt(key), "") ?: ""
        return decrypt(string)
    }

    fun getBoolean(key: String): Boolean {
        return mPref!!.getBoolean(encrypt(key), false)
    }


    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return mPref!!.getBoolean(encrypt(key), defaultValue)
    }

    fun <T> getObject(key: String, clazz: Class<T>): T? {
        if (mPref != null) {
            val gson = Gson()
            val json = mPref.getString(encrypt(key), "")
            return gson.fromJson(decrypt(json), clazz)
        } else
            return null
    }

    fun saveObject(key: String, `object`: Any) {
        if (mPref != null) {
            val gson = Gson()
            val json = gson.toJson(`object`)
            mPref.edit().putString(encrypt(key), encrypt(json)).apply()
        }
    }

    fun remove(key: String) {
        mPref!!.edit()
            .remove(encrypt(key))
            .apply()
    }

    fun clear(): Boolean {
        return mPref!!.edit()
            .clear()
            .commit()
    }

    private fun encrypt(input: String): String {
        return Base64.encodeToString(input.toByteArray(), Base64.DEFAULT)
    }

    private fun decrypt(input: String): String {
        return String(Base64.decode(input, Base64.DEFAULT))
    }
}