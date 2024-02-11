package com.example.bookclub.data

class RegisterRepository(val dataSource: RegisterDataSource) {
    fun register(contact: String, password: String): Result<Boolean> {
        return dataSource.register(contact, password)
    }

    fun createProfile (): Result<Boolean> {
        val result = dataSource.createProfile()

        if (result is Result.Success) {
            val loginRepository = LoginRepository(dataSource = LoginDataSource())
            loginRepository.login("ad", "ad") // TODO:
        }

        return result
    }
}