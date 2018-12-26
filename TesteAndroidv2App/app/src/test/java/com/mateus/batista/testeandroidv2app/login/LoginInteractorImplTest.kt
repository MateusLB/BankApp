package com.mateus.batista.testeandroidv2app.login

import com.mateus.batista.testeandroidv2app.core.login.LoginInteractor
import com.mateus.batista.testeandroidv2app.core.login.LoginInteractorImpl
import com.mateus.batista.testeandroidv2app.data.local.repository.LocalRepository
import com.mateus.batista.testeandroidv2app.data.remote.Response
import com.mateus.batista.testeandroidv2app.data.remote.repository.RemoteRepository
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
    private lateinit var remoteRepository: RemoteRepository
    @Mock
    private lateinit var localRepository: LocalRepository
    private lateinit var loginInteractor: LoginInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        loginInteractor = LoginInteractorImpl(localRepository, remoteRepository)
    }

    @Test fun `signIn WHEN remoteRepository returns a success response MUST return a SUCCESS`(){
        val mockedLoginBody = LoginFactory.createValidLoginBody()
        val mockedSuccessLoginResponse = LoginFactory.createSuccessLoginResponse()
        runBlocking {
            doReturn(mockedSuccessLoginResponse).`when`(remoteRepository).signIn(mockedLoginBody)
            assert(loginInteractor.signIn(mockedLoginBody) is Response.Success)
            verify(remoteRepository).signIn(mockedLoginBody)
        }
    }

    @Test fun `signIn WHEN remoteRepository returns a error response MUST return a ERROR`(){
        val mockedLoginBody = LoginFactory.createValidLoginBody()
        val mockedErrorLoginResponse = LoginFactory.createErrorLoginResponse()
        runBlocking {
            doReturn(mockedErrorLoginResponse).`when`(remoteRepository).signIn(mockedLoginBody)
            assert(loginInteractor.signIn(mockedLoginBody) is Response.Error)
            verify(remoteRepository).signIn(mockedLoginBody)
        }
    }

    @Test
    fun `setRecentLogin WHEN passed a LoginBody MUST save recentLogin in sharedPreferences`() {
        val mockedLoginBody = LoginFactory.createValidLoginBody()
        loginInteractor.setRecentLogin(mockedLoginBody)
        verify(localRepository, times(1)).setRecentLogin(mockedLoginBody)
    }

    @Test fun `getRecentLogin MUST return LoginBody`(){
        val mockedLoginBody = LoginFactory.createValidLoginBody()
        doReturn(mockedLoginBody).`when`(localRepository).getRecentLogin()
        loginInteractor.getRecentLogin()
        assert(localRepository.getRecentLogin() == mockedLoginBody)
    }

    @Test
    fun `saveUserAccount WHEN passed a UserAccountEntity MUST save UserAccountEntity in Room`() {
        val mockedUserAccountEntity = LoginFactory.createUserAccountEntity()
        loginInteractor.saveUserAccount(mockedUserAccountEntity)
        verify(localRepository, times(1)).saveUserAccount(mockedUserAccountEntity)
    }
}