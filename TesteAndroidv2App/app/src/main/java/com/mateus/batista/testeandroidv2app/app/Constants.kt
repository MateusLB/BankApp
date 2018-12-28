package com.mateus.batista.testeandroidv2app.app

class Constants {

    class SharedPreferences {
        companion object {
            const val PREF_NAME = "bank_pref"
            const val RECENT_LOGIN = "recent_login"
            const val IS_LOGGED = "is_logged"
        }
    }

    class UtilConstants {
        companion object {
            const val PASSWORD_REGEX: String = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{1,}\$"
        }
    }

    class Database{
        companion object {
            const val DATABASE_NAME = "database-bank"
        }
    }

    class Service{
        companion object {
            const val SERVER_ERROR = 500
        }
    }
}