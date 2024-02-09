package com.example.bookclub.data

import com.example.bookclub.data.model.LoggedInUser
import java.io.IOException
import java.util.UUID

class RegisterDataSource {
    fun register(username: String, password: String): Result<Boolean> {
        try {
            // TODO: register user

            return Result.Success(true)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun createProfile() : Result<Boolean>{
        try {

            // TODO: create user profile and
            // TODO: handle loggedInUser authentication
            return Result.Success(true)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }
}