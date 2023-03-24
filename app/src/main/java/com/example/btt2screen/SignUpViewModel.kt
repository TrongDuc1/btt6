package com.example.btt2screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class SignUpViewModel : ViewModel() {
    val fullName = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private var _isErrorEvent: MutableLiveData<String> = MutableLiveData()
    val isErrorEvent: LiveData<String>
        get() = _isErrorEvent

    private var _isSuccessEvent: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccessEvent: LiveData<Boolean>
        get() = _isSuccessEvent


    fun onSignUp() {
        var _errorString = ""


        if (fullName.value.isNullOrEmpty())
            _errorString += "Your full name is null or empty"


        if (!isEmailValid(email.value.toString()))
        {
            if(_errorString.isNotEmpty()) _errorString += "\n"
            _errorString += "Invalid E-mail"
        }

        if(isExistEmail(email.value.toString()))
        {
            if(_errorString.isNotEmpty()) _errorString += "\n"
            _errorString += "This email is already in use"
        }

        if (!isPasswordValid(password.value.toString()))
        {
            if(_errorString.isNotEmpty()) _errorString += "\n"
            _errorString += "Invalid Password"
        }

        // Throw Error
        if(_errorString.isNotEmpty())
            return _isErrorEvent.postValue(_errorString)

        _isSuccessEvent.postValue(true)

    }

    private fun isExistEmail(email: String): Boolean {
        val _isExistEmail = DataStore.userDataStore.find{ userData -> userData["email"] == email }?.isNotEmpty()
        return _isExistEmail == true

    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    companion object {
        private val Validate_password: Pattern = Pattern.compile(
//            "^" + "(?=.*[0-9])" + "(?=.*[a-z])" + "(?=.*[A-Z])" + "(?=.*[!@#$%^&*()])"+".{8,}" + "$"
            "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$"
        )
    }
    private fun isPasswordValid(password: String): Boolean {
        return Validate_password.matcher(password).matches()
    }

}