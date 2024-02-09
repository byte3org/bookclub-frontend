package com.example.bookclub.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.bookclub.R
import com.example.bookclub.databinding.ActivityRegisterContactBinding

class RegisterContactActivity : AppCompatActivity() {
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var binding: ActivityRegisterContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_contact)

        val contact = binding.inputContact
        val verify = binding.btnVerifyContact

        registerViewModel = ViewModelProvider(this, RegisterViewModelFactory())[RegisterViewModel::class.java]

        verify.setOnClickListener {
            if (contact.text.isNotEmpty() && registerViewModel.registerContactChanged(contact = contact.text.toString())) {
                val registerCreateProfile = Intent(this, RegisterCreateProfileActivity::class.java)
                registerCreateProfile.putExtra("contact", contact.text.toString())
                startActivity(registerCreateProfile)
            }
        }
    }
}