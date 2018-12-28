package com.mateus.batista.testeandroidv2app.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mateus.batista.testeandroidv2app.TestContextProvider
import com.mateus.batista.testeandroidv2app.core.login.LoginInteractor
import com.mateus.batista.testeandroidv2app.core.login.LoginViewModel
import com.mateus.batista.testeandroidv2app.data.mapper.UserAccountMapper
import com.mateus.batista.testeandroidv2app.login.LoginFactory.createErrorLoginResponse
import com.mateus.batista.testeandroidv2app.login.LoginFactory.createInvalidLoginBody
import com.mateus.batista.testeandroidv2app.login.LoginFactory.createSuccessLoginResponse
import com.mateus.batista.testeandroidv2app.login.LoginFactory.createValidLoginBody
import com.mateus.batista.testeandroidv2app.utils.FieldStatus
import com.mateus.batista.testeandroidv2app.utils.FlowState

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import kotlin.test.assertFalse
import kotlin.test.assertNotNull

@RunWith(JUnit4::class)
class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var loginInteractor: LoginInteractor
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = LoginViewModel(loginInteractor, TestContextProvider())
    }

    @Test
    fun `isUserValid WHEN passed an empty MUST return false`() {
        assertFalse(viewModel.isUserValid(""))
    }

    @Test
    fun `isUserValid WHEN passed an empty MUST set an EMPTY status in userFieldStatus`() {
        viewModel.isUserValid("")
        assert(viewModel.getUserFieldStatus().value == FieldStatus.EMPTY)
    }

    @Test
    fun `isUserValid WHEN passed an invalid email MUST return false`() {
        assertFalse(viewModel.isUserValid("myemail"))
    }

    @Test
    fun `isUserValid WHEN passed an invalid CPF MUST return false`() {
        assertFalse(viewModel.isUserValid("08621045"))
    }

    @Test
    fun `isUserValid WHEN passed an invalid value MUST set an INVALID status in userFieldStatus`() {
        viewModel.isUserValid("dsad123")
        assert(viewModel.getUserFieldStatus().value == FieldStatus.INVALID)
    }

    @Test
    fun `isUserValid WHEN passed an valid email MUST return true`() {
        assert(viewModel.isUserValid("myemail@bank.com"))
    }

    @Test
    fun `isUserValid WHEN passed an valid email MUST set an VALID status in userFieldStatus`() {
        viewModel.isUserValid("myemail@bank.com")
        assert(viewModel.getUserFieldStatus().value == FieldStatus.VALID)
    }

    @Test
    fun `isUserValid WHEN passed an valid CPF MUST return true`() {
        assert(viewModel.isUserValid("00037064002"))
    }

    @Test
    fun `isUserValid WHEN passed an valid CPF MUST set an VALID status in userFieldStatus`() {
        viewModel.isUserValid("023.747.070-59")
        assert(viewModel.getUserFieldStatus().value == FieldStatus.VALID)
    }

    @Test
    fun `isPasswordValid WHEN passed an empty MUST return false`() {
        assertFalse(viewModel.isPasswordValid(""))
    }

    @Test
    fun `isPasswordValid WHEN passed an empty MUST set an EMPTY status in userFieldStatus`() {
        viewModel.isPasswordValid("")
        assert(viewModel.getPasswordFieldStatus().value == FieldStatus.EMPTY)
    }

    @Test
    fun `isPasswordValid WHEN passed an invalid value MUST return false`() {
        assertFalse(viewModel.isPasswordValid("a1q@"))
    }

    @Test
    fun `isPasswordValid WHEN passed an invalid value MUST set an INVALID status in passwordFieldStatus`() {
        viewModel.isPasswordValid("a1q@")
        assert(viewModel.getPasswordFieldStatus().value == FieldStatus.INVALID)
    }

    @Test
    fun `isPasswordValid WHEN passed an valid value MUST return true`() {
        assert(viewModel.isPasswordValid("A1q@"))
    }

    @Test
    fun `isPasswordValid WHEN passed an valid value MUST set an VALID status in passwordFieldStatus`() {
        viewModel.isPasswordValid("A1q@")
        assert(viewModel.getPasswordFieldStatus().value == FieldStatus.VALID)
    }

    @Test
    fun `processSignIn WHEN passed valid values MUST call API`() {
        val mockedLoginBody = createValidLoginBody()
        runBlocking {
            viewModel.processSignIn(mockedLoginBody)
            verify(loginInteractor, times(1)).signIn(mockedLoginBody)
        }
    }

    @Test
    fun `processSignIn WHEN passed invalid values MUST NOT call API`() {
        val mockedLoginBody = createInvalidLoginBody()
        runBlocking {
            viewModel.processSignIn(mockedLoginBody)
            verifyZeroInteractions(loginInteractor)
        }
    }

    @Test
    fun `processSignIn WHEN API returns success MUST set a SUCCES FlowState in signInStatus`() {

        val mockedLoginBody = createValidLoginBody()
        val mockedSuccessLoginResponse = createSuccessLoginResponse()
        runBlocking {
            doReturn(mockedSuccessLoginResponse).`when`(loginInteractor).signIn(mockedLoginBody)
            viewModel.processSignIn(mockedLoginBody)
        }
        assert(viewModel.getSignInStatus().value?.status == FlowState.Status.SUCCESS)
    }

    @Test
    fun `processSignIn WHEN API returns success MUST save recentLogin in sharedPreferences`() {
        val mockedLoginBody = createValidLoginBody()
        val mockedSuccessLoginResponse = createSuccessLoginResponse()
        runBlocking {
            doReturn(mockedSuccessLoginResponse).`when`(loginInteractor).signIn(mockedLoginBody)
            viewModel.processSignIn(mockedLoginBody)
        }
        verify(loginInteractor, times(1)).setRecentLogin(mockedLoginBody)
    }

    @Test
    fun `processSignIn WHEN API returns success MUST save isLogged in sharedPreferences`() {
        val mockedLoginBody = createValidLoginBody()
        val mockedSuccessLoginResponse = createSuccessLoginResponse()
        runBlocking {
            doReturn(mockedSuccessLoginResponse).`when`(loginInteractor).signIn(mockedLoginBody)
            viewModel.processSignIn(mockedLoginBody)
        }
        verify(loginInteractor, times(1)).setIsLogged()
    }

    @Test
    fun `processSignIn WHEN API returns success MUST save saveUserDetail in Room`() {
        val mockedLoginBody = createValidLoginBody()
        val mockedSuccessLoginResponse = createSuccessLoginResponse()
        runBlocking {
            doReturn(mockedSuccessLoginResponse).`when`(loginInteractor).signIn(mockedLoginBody)
            viewModel.processSignIn(mockedLoginBody)
        }
        verify(loginInteractor, times(1)).saveUserAccount(
            UserAccountMapper.parse(mockedSuccessLoginResponse.data.userAccount!!)
        )
    }

    @Test
    fun `processSignIn WHEN API returns error MUST set a ERROR FlowState in signInStatus`() {

        val mockedLoginBody = createValidLoginBody()
        val mockedErrorLoginResponse = createErrorLoginResponse()
        runBlocking {
            doReturn(mockedErrorLoginResponse).`when`(loginInteractor).signIn(mockedLoginBody)
            viewModel.processSignIn(mockedLoginBody)
        }
        assert(viewModel.getSignInStatus().value?.status == FlowState.Status.ERROR)
    }

    @Test
    fun `processSignIn WHEN API returns error MUST set a not null throwable in signInStatus`() {
        val mockedLoginBody = createValidLoginBody()
        val mockedErrorLoginResponse = createErrorLoginResponse()
        runBlocking {
            doReturn(mockedErrorLoginResponse).`when`(loginInteractor).signIn(mockedLoginBody)
            viewModel.processSignIn(mockedLoginBody)
        }
        assertNotNull(viewModel.getSignInStatus().value?.error)
    }
}