package com.mateus.batista.testeandroidv2app.app

class Constants {

    class SharedPreferences {
        companion object {
            const val PREF_NAME = "bank_pref"
        }
    }

    class UtilConstants {
        companion object {
            const val PASSWORD_REGEX: String = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{1,}\$"
        }
    }
}