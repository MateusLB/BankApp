package com.mateus.batista.testeandroidv2app.data.local.repository

import com.mateus.batista.testeandroidv2app.data.local.entity.UserAccountEntity

interface BankPostingsLocalRepository {
     fun getUserAccount(): UserAccountEntity
     fun logout()
}