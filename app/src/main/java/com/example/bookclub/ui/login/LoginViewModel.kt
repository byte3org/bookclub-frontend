package com.example.bookclub.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.example.bookclub.data.LoginRepository
import com.example.bookclub.data.Result

import com.example.bookclub.R

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(contact: String, password: String) {
        // can be launched in a separate asynchronous job
        val result = loginRepository.login(contact, password)

        if (result is Result.Success) {
            _loginResult.value =
                LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

    fun loginDataChanged(contact: String, password: String) {
        if (!isContactNumberValid(contact)) {
            _loginForm.value = LoginFormState(contactError = R.string.invalid_contact)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isContactNumberValid(contactNumber: String): Boolean {
        // Regular expression to validate contact numbers
        val regex = Regex("^\\+(?:[0-9] ?){6,14}[0-9]$")

        // Check if the contact number matches the regular expression
        return regex.matches(contactNumber)
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}