package com.example.bookclub.ui.register

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import com.example.bookclub.R
import com.example.bookclub.databinding.ActivityRegisterCreaterProfileBinding
import com.example.bookclub.ui.login.afterTextChanged

class RegisterCreateProfileActivity : AppCompatActivity() {
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var binding: ActivityRegisterCreaterProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_creater_profile)

        val contact = intent.getStringExtra("contact")
        val firstName = binding.inputFirstName
        val secondName = binding.inputSecondName
        val username = binding.inputUsername
        val email = binding.inputEmail
        val password = binding.inputPassword
        val createProfile = binding.btnCreateProfile

        registerViewModel = ViewModelProvider(this, RegisterViewModelFactory())[RegisterViewModel::class.java]

        registerViewModel.registerFormState.observe(this@RegisterCreateProfileActivity, Observer {
            val registerState = it ?: return@Observer

            // disable login button unless both username / password is valid
            if (createProfile != null) {
                createProfile.isEnabled = registerState.isDataValid
            }
            if (registerState.emailError != null) {
                if (contact != null) {
                    email.error = getString(registerState.emailError)
                }
            }
            if (registerState.usernameError != null) {
                if (contact != null) {
                    username.error = getString(registerState.usernameError)
                }
            }
            if (registerState.firstNameError != null) {
                if (contact != null) {
                    firstName.error = getString(registerState.firstNameError)
                }
            }
            if (registerState.secondNameError != null) {
                if (contact != null) {
                    secondName.error = getString(registerState.secondNameError)
                }
            }
            if (registerState.passwordError != null) {
                if (password != null) {
                    password.error = getString(registerState.passwordError)
                }
            }
        })

        registerViewModel.registerResult.observe(this@RegisterCreateProfileActivity, Observer {
            val registerResult = it ?: return@Observer

            if (registerResult.error != null) {
                showRegistratoFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        contact?.afterTextChanged {
            if (password != null) {
                loginViewModel.loginDataChanged(
                    contact.text.toString(),
                    password.text.toString()
                )
            }
        }

        password?.apply {
            afterTextChanged {
                if (contact != null) {
                    loginViewModel.loginDataChanged(
                        contact.text.toString(),
                        password.text.toString()
                    )
                }
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        if (contact != null) {
                            loginViewModel.login(
                                contact.text.toString(),
                                password.text.toString()
                            )
                        }
                }
                false
            }

            if (login != null) {
                login.setOnClickListener {
                    if (contact != null) {
                        loginViewModel.login(contact.text.toString(), password.text.toString())
                    }
                }
            }
        }
    }
}