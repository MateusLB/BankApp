package com.mateus.batista.testeandroidv2app.bankPostings

import com.mateus.batista.testeandroidv2app.core.bankPostings.BankPostingsInteractor
import com.mateus.batista.testeandroidv2app.core.bankPostings.BankPostingsInteractorImp
import com.mateus.batista.testeandroidv2app.data.local.repository.BankPostingsLocalRepository
import com.mateus.batista.testeandroidv2app.data.remote.Response
import com.mateus.batista.testeandroidv2app.data.remote.repository.BankPostingsRemoteRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class BankPostingsInteractorImpTest {

    @Mock
    private lateinit var bankPostingsLocalRepository: BankPostingsLocalRepository
    @Mock
    private lateinit var bankPostingsRemoteRepository: BankPostingsRemoteRepository

    private lateinit var bankPostingsInteractor: BankPostingsInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        bankPostingsInteractor = BankPostingsInteractorImp(bankPostingsLocalRepository, bankPostingsRemoteRepository)
    }

    @Test
    fun `getStatements WHEN remoteRepository returns a success response MUST return a SUCCESS`() {
        val mockedSuccessStatementsResponse = BankPostingsFactory.createSuccessStatementsResponse()
        val userId = 0
        runBlocking {
            doReturn(mockedSuccessStatementsResponse).`when`(bankPostingsRemoteRepository).getStatements(userId)
            assert(bankPostingsInteractor.getStatements(userId) is Response.Success)
            verify(bankPostingsRemoteRepository).getStatements(userId)
        }

    }

    @Test
    fun `getStatements WHEN remoteRepository returns a error response MUST return a ERROR`() {
        val mockedErrorStatementsResponse = BankPostingsFactory.createErrorStatementsResponse()
        val userId = 0
        runBlocking {
            doReturn(mockedErrorStatementsResponse).`when`(bankPostingsRemoteRepository).getStatements(userId)
            assert(bankPostingsInteractor.getStatements(userId) is Response.Error)
            verify(bankPostingsRemoteRepository).getStatements(userId)
        }
    }

    @Test
    fun `getUserAccount MUST return UserAccountEntity`() {
        val mockedUserAccount = BankPostingsFactory.createUserAccountEntity()
        doReturn(mockedUserAccount).`when`(bankPostingsLocalRepository).getUserAccount()
        bankPostingsInteractor.getUserAccount()
        assert(bankPostingsLocalRepository.getUserAccount() == mockedUserAccount)
    }

    @Test
    fun `logout MUST call logout`() {
        bankPostingsInteractor.logout()
        verify(bankPostingsLocalRepository, times(1)).logout()
    }
}