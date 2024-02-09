package com.example.bookclub.ui.register

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookclub.R
import com.example.bookclub.data.RegisterRepository
import com.example.bookclub.ui.login.LoginFormState
import com.example.bookclub.ui.login.LoginResult

class RegisterViewModel(private val registerRepository: RegisterRepository) : ViewModel() {
    private val _registerForm = MutableLiveData<RegisterFormState>()
    val registerFormState: LiveData<RegisterFormState> = _registerForm

    private val _registerResult = MutableLiveData<LoginResult>()
    val registerResult: LiveData<LoginResult> = _registerResult

    fun registerContactChanged(contact: String): Boolean {
        return isContactNumberValid(contact)
    }

    private fun isContactNumberValid(contactNumber: String): Boolean {
        // Regular expression to validate contact numbers
        val regex = Regex("^\\+(?:[0-9] ?){6,14}[0-9]$")

        // Check if the contact number matches the regular expression
        return regex.matches(contactNumber)
    }

    fun registerDataChanged(username: String, firstName : String, secondName: String, email: String, password: String) {
        if (!isEmailValid(email)) {
            _registerForm.value = RegisterFormState(emailError = R.string.invalid_email)
        }  else if (!isNameValid(firstName)) {
            _registerForm.value = RegisterFormState(firstNameError = R.string.invalid_name)
        } else if (!isNameValid(secondName)) {
            _registerForm.value = RegisterFormState(secondNameError = R.string.invalid_name)
        } else if (!isNameValid(username)) {
            _registerForm.value = RegisterFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _registerForm.value = RegisterFormState(passwordError = R.string.invalid_password)
        } else {
            _registerForm.value = RegisterFormState(isDataValid = true)
        }
    }


    private fun isEmailValid(email: String): Boolean {
        val regex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+\$")
        return regex.matches(email)
    }

    private fun isNameValid(name: String): Boolean {
        return name.length < 10
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

}