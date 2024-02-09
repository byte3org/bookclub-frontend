package com.example.bookclub.data

class RegisterRepository(val dataSource: RegisterDataSource) {
    fun register() {

    }

    fun createProfile () {
        val result = dataSource.createProfile()

        if (result is Result.Success) {
            val loginRepository = LoginRepository(dataSource = LoginDataSource())
            loginRepository.login("ad", "ad") // TODO:
        }
    }
}