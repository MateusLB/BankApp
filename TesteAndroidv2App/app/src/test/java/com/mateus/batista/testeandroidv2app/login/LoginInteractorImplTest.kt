package com.mateus.batista.testeandroidv2app.login

import com.mateus.batista.testeandroidv2app.core.login.LoginInteractor
import com.mateus.batista.testeandroidv2app.core.login.LoginInteractorImpl
import com.mateus.batista.testeandroidv2app.data.local.repository.LoginLocalRepository
import com.mateus.batista.testeandroidv2app.data.remote.Response
import com.mateus.batista.testeandroidv2app.data.remote.repository.LoginRemoteRepository
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
class LoginInteractorImplTest {

    @Mock
    private lateinit var loginRemoteRepository: LoginRemoteRepository
    @Mock
    private lateinit var loginLocalRepository: LoginLocalRepository
    private lateinit var loginInteractor: LoginInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        loginInteractor = LoginInteractorImpl(loginLocalRepository, loginRemoteRepository)
    }

    @Test
    fun `signIn WHEN remoteRepository returns a success response MUST return a SUCCESS`() {
        val mockedLoginBody = LoginFactory.createValidLoginBody()
        val mockedSuccessLoginResponse = LoginFactory.createSuccessLoginResponse()
        runBlocking {
            doReturn(mockedSuccessLoginResponse).`when`(loginRemoteRepository).signIn(mockedLoginBody)
            assert(loginInteractor.signIn(mockedLoginBody) is Response.Success)
            verify(loginRemoteRepository).signIn(mockedLoginBody)
        }
    }

    @Test
    fun `signIn WHEN remoteRepository returns a error response MUST return a ERROR`() {
        val mockedLoginBody = LoginFactory.createValidLoginBody()
        val mockedErrorLoginResponse = LoginFactory.createErrorLoginResponse()
        runBlocking {
            doReturn(mockedErrorLoginResponse).`when`(loginRemoteRepository).signIn(mockedLoginBody)
            assert(loginInteractor.signIn(mockedLoginBody) is Response.Error)
            verify(loginRemoteRepository).signIn(mockedLoginBody)
        }
    }

    @Test
    fun `setIsLogged MUST save isLogged in sharedPreferences`() {
        loginInteractor.setIsLogged()
        verify(loginLocalRepository, times(1)).setIsLogged()
    }

    @Test
    fun `setRecentLogin WHEN passed a LoginBody MUST save recentLogin in sharedPreferences`() {
        val mockedLoginBody = LoginFactory.createValidLoginBody()
        loginInteractor.setRecentLogin(mockedLoginBody)
        verify(loginLocalRepository, times(1)).setRecentLogin(mockedLoginBody)
    }

    @Test
    fun `getRecentLogin MUST return LoginBody`() {
        val mockedLoginBody = LoginFactory.createValidLoginBody()
        doReturn(mockedLoginBody).`when`(loginLocalRepository).getRecentLogin()
        loginInteractor.getRecentLogin()
        assert(loginLocalRepository.getRecentLogin() == mockedLoginBody)
    }

    @Test
    fun `saveUserAccount WHEN passed a UserAccountEntity MUST save UserAccountEntity in Room`() {
        val mockedUserAccountEntity = LoginFactory.createUserAccountEntity()
        loginInteractor.saveUserAccount(mockedUserAccountEntity)
        verify(loginLocalRepository, times(1)).saveUserAccount(mockedUserAccountEntity)
    }
}