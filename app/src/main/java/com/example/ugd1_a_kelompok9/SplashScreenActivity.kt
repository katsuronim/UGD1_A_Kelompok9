package com.example.ugd1_a_kelompok9

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.window.SplashScreen
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ugd1_a_kelompok9.Activity.LoginActivity
import java.lang.Boolean


class SplashScreenActivity : AppCompatActivity() {
    lateinit var splashScreen: FrameLayout
    var prevStarted = "yes"
    override fun onResume() {
        super.onResume()
        val sharedpreferences =
            getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        if (!sharedpreferences.getBoolean(prevStarted, false)) {
            val editor = sharedpreferences.edit()
            editor.putBoolean(prevStarted, Boolean.TRUE)
            editor.apply()
            val splashScreen = findViewById<FrameLayout>(R.id.splashscreen)
        } else {
            moveToSecondary()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }

    fun moveToSecondary() {
        // use an intent to travel from one activity to another.
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}

//@Suppress("DEPRECATION")
//class SplashScreenActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash_screen)
//
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )
//
////        Handler(Looper.getMainLooper()).postDelayed({
////            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
////            startActivity(intent)
////            finish()
////        }, 3000) // 3000 is the delayed time in milliseconds.
//
//        Handler().postDelayed({
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }, 3000)
//    }
//}