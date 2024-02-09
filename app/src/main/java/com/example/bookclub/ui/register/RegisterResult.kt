package com.example.bookclub.ui.register

import com.example.bookclub.ui.login.LoggedInUserView

/**
 * Authentication result : success (user details) or error message.
 */
data class RegisterResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)