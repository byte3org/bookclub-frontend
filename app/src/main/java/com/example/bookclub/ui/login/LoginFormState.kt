package com.example.bookclub.ui.login

/**
 * Data validation state of the login form.
 */
data class LoginFormState(
    val contactError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)