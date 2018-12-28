package com.mateus.batista.testeandroidv2app.bankPostings

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mateus.batista.testeandroidv2app.TestContextProvider
import com.mateus.batista.testeandroidv2app.bankPostings.BankPostingsFactory.createErrorStatementsResponse
import com.mateus.batista.testeandroidv2app.bankPostings.BankPostingsFactory.createSuccessStatementsResponse
import com.mateus.batista.testeandroidv2app.bankPostings.BankPostingsFactory.createUserAccountEntity
import com.mateus.batista.testeandroidv2app.core.bankPostings.BankPostingsInteractor
import com.mateus.batista.testeandroidv2app.core.bankPostings.BankPostingsViewModel
import com.mateus.batista.testeandroidv2app.utils.FlowState
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class BankPostingsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var bankPostingsInteractor: BankPostingsInteractor
    private lateinit var viewModel: BankPostingsViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = BankPostingsViewModel(bankPostingsInteractor, TestContextProvider())
    }

    @Test
    fun `getUserAccount WHEN BD returns UserAccount MUST set a SUCCES FlowState in userAccountState`() {
        val mockedUserAccount = createUserAccountEntity()
        runBlocking {
            doReturn(mockedUserAccount).`when`(bankPostingsInteractor).getUserAccount()
            viewModel.getUserAccount()
        }
        assert(viewModel.getUserAccountState().value?.status == FlowState.Status.SUCCESS)
    }

    @Test
    fun `getStatements WHEN API returns success MUST set a SUCCES FlowState in statementsState`() {
        val mockedSuccessStatementsResponse = createSuccessStatementsResponse()
        val userId = 0
        runBlocking {
            doReturn(mockedSuccessStatementsResponse).`when`(bankPostingsInteractor).getStatements(userId)
            viewModel.getStatements()
        }
        assert(viewModel.getStatementsState().value?.status == FlowState.Status.SUCCESS)
    }

    @Test
    fun `getStatements WHEN API returns error MUST set a ERROR FlowState in statementsState`() {
        val mockedErrorStatementsResponse = createErrorStatementsResponse()
        val userId = 0
        runBlocking {
            doReturn(mockedErrorStatementsResponse).`when`(bankPostingsInteractor).getStatements(userId)
            viewModel.getStatements()
        }
        assert(viewModel.getStatementsState().value?.status == FlowState.Status.ERROR)
    }

    @Test
    fun `logout MUST call logout`() {
        runBlocking {
            viewModel.logout()
            verify(bankPostingsInteractor, times(1)).logout()
        }
    }
}