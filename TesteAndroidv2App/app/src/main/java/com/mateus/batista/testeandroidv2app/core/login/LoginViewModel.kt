package com.mateus.batista.testeandroidv2app.core.login

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mateus.batista.testeandroidv2app.app.Constants.UtilConstants.Companion.PASSWORD_REGEX
import com.mateus.batista.testeandroidv2app.base.viewModel.BaseViewModel
import com.mateus.batista.testeandroidv2app.utils.CPFUtil
import com.mateus.batista.testeandroidv2app.utils.FieldStatus
import javax.inject.Inject

class LoginViewModel @Inject constructor() : BaseViewModel() {

    private val userFieldStatus by lazy { MutableLiveData<FieldStatus>() }
    private val passwordFieldStatus by lazy { MutableLiveData<FieldStatus>() }

    fun getUserFieldStatus(): LiveData<FieldStatus> = userFieldStatus
    fun getPasswordFieldStatus(): LiveData<FieldStatus> = passwordFieldStatus

    fun processSignIn(user: String, password: String){
        if(isUserValid(user) && isPasswordValid(password)){

        }
    }

    fun isUserValid(user: String): Boolean = when {
        user.isEmpty() -> {
            userFieldStatus.value = FieldStatus.EMPTY
            false
        }
        !isEmailValid(user) && !isCPFValid(user) ->{
            userFieldStatus.value = FieldStatus.INVALID
            false
        }
        else -> {
            userFieldStatus.value = FieldStatus.VALID
            true
        }
    }

    fun isEmailValid(email: String): Boolean =
        PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()

    fun isCPFValid(cpf: String): Boolean = CPFUtil.myValidateCPF(cpf)

    fun isPasswordValid(password: String): Boolean = when {
        password.isEmpty() -> {
            passwordFieldStatus.value = FieldStatus.EMPTY
            false
        }
        !(password.contains(Regex(PASSWORD_REGEX))) -> {
            passwordFieldStatus.value = FieldStatus.INVALID
            false
        }
        else -> {
            passwordFieldStatus.value = FieldStatus.VALID
            true
        }
    }
}