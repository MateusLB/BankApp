package com.mateus.batista.testeandroidv2app.core.bankPostings

import com.mateus.batista.testeandroidv2app.data.local.entity.UserAccountEntity
import com.mateus.batista.testeandroidv2app.data.local.repository.BankPostingsLocalRepository
import com.mateus.batista.testeandroidv2app.data.remote.Response
import com.mateus.batista.testeandroidv2app.data.remote.model.StatementResponse
import com.mateus.batista.testeandroidv2app.data.remote.repository.BankPostingsRemoteRepository
import javax.inject.Inject

class BankPostingsInteractorImp @Inject constructor(
    private val bankPostingsLocalRepository: BankPostingsLocalRepository,
    private val bankPostingsRemoteRepository: BankPostingsRemoteRepository
) : BankPostingsInteractor {

    override suspend fun getStatements(userId: Int): Response<StatementResponse> {
        return bankPostingsRemoteRepository.getStatements(userId)
    }

    override fun getUserAccount(): UserAccountEntity {
       return bankPostingsLocalRepository.getUserAccount()
    }

    override fun logout() {

    }
}