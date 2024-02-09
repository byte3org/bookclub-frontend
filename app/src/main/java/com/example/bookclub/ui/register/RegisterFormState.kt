package com.example.bookclub.ui.register

data class RegisterFormState (
    val firstNameError: Int? = null,
    val secondNameError: Int? = null,
    val usernameError: Int? = null,
    val emailError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)