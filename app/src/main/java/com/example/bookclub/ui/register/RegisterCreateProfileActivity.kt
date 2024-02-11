package com.example.bookclub.ui.register

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import com.example.bookclub.R
import com.example.bookclub.data.Result
import com.example.bookclub.databinding.ActivityRegisterCreaterProfileBinding
import com.example.bookclub.ui.login.LoggedInUserView
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
                showRegisterFailed(registerResult.error)
            }
            if (registerResult.success != null) {
                updateUiWithUser(registerResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        firstName.afterTextChanged {
            registerViewModel.registerDataChanged(
                username.text.toString(),
                firstName.text.toString(),
                secondName.text.toString(),
                email.text.toString(),
                password.text.toString()
            )
        }

        secondName.afterTextChanged {
            registerViewModel.registerDataChanged(
                username.text.toString(),
                firstName.text.toString(),
                secondName.text.toString(),
                email.text.toString(),
                password.text.toString()
            )
        }

        username.afterTextChanged {
            registerViewModel.registerDataChanged(
                username.text.toString(),
                firstName.text.toString(),
                secondName.text.toString(),
                email.text.toString(),
                password.text.toString()
            )
        }

        email.afterTextChanged {
            registerViewModel.registerDataChanged(
                username.text.toString(),
                firstName.text.toString(),
                secondName.text.toString(),
                email.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                if (contact != null) {
                    registerViewModel.registerDataChanged(
                        username.text.toString(),
                        firstName.text.toString(),
                        secondName.text.toString(),
                        email.text.toString(),
                        password.text.toString()
                    )
                }
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        if (contact != null) {
                            registerViewModel.registerDataChanged(
                                username.text.toString(),
                                firstName.text.toString(),
                                secondName.text.toString(),
                                email.text.toString(),
                                password.text.toString()
                            )
                        }
                }
                false
            }

            if (createProfile != null) {
                createProfile.setOnClickListener {
                    if (contact != null) {
                        val result = registerViewModel.registerAccount(contact, username.text.toString(), email.text.toString(), firstName.text.toString(), secondName.text.toString(), password.text.toString())
                        if (result is Result.Success) {
                            // goto home
                        } else {
                            // go
                        }
                    }
                }
            }
        }
    }
    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showRegisterFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}