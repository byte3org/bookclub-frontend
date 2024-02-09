package com.example.bookclub

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookclub.ui.login.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        // Example: Delay for 2 seconds then navigate to main activity
        val splashTimeOut: Long = 2000
        val mainIntent = Intent(this, MainActivity::class.java)
        val loginIntent = Intent(this, LoginActivity::class.java)
        //val registerIntent = Intent(this)

        // Start main activity after delay
        Thread(Runnable {
            try {
                Thread.sleep(splashTimeOut)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } finally {
                startActivity(mainIntent)
                finish() // Finish splash activity so it can't be returned to.
            }
        }).start()
    }
}