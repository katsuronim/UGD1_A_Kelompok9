package com.example.ugd1_a_kelompok9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.room.Room
import com.example.ugd1_a_kelompok9.room.UserDB
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    private lateinit var inputUsername: TextInputLayout
    private lateinit var inputPassword: TextInputLayout
    private lateinit var mainLayout: ConstraintLayout
    private lateinit var sharePreference: SharePreference
    private lateinit var db: UserDB
    lateinit var mBundle: Bundle
    lateinit var vUsername: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        getBundle()
        sharePreference = SharePreference(this)
        inputUsername = findViewById(R.id.inputLayoutUsername)
        inputPassword = findViewById(R.id.inputLayoutPassword)
        mainLayout = findViewById(R.id.mainLayout)
        val btnClear: Button = findViewById(R.id.btnClear)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val btnRegister: Button = findViewById(R.id.btnRegister)

        btnClear.setOnClickListener{
            inputUsername.getEditText()?.setText("")
            inputPassword.getEditText()?.setText("")

            Snackbar.make(mainLayout, "Text Cleared Success", Snackbar.LENGTH_LONG).show()
        }

        btnRegister.setOnClickListener{
            val moveRegister = Intent(this, RegisterActivity::class.java)
            startActivity(moveRegister)
        }

        btnLogin.setOnClickListener(View.OnClickListener {
            var checkLogin = false
            val username: String = inputUsername.getEditText()?.getText().toString()
            val password: String = inputPassword.getEditText()?.getText().toString()
            db = Room.databaseBuilder(applicationContext, UserDB::class.java, "appUser.db").build()
            var userId: Int = 0
            var pasId: Int = 0
            var checkUser: String
            var checkPass: String

            if (username.isEmpty()) {
                inputUsername.setError("Username must be filled with text")
                checkLogin = false
            }
            if (password.isEmpty()) {
                inputPassword.setError("Password must be filled with text")
                checkLogin = false
            }

            CoroutineScope(Dispatchers.IO).launch {
                val user = db.userDao().getUser(username, password)

                if (user == null) {
                    Log.d("LoginActivity", "USER IS NULL")
                    withContext(Dispatchers.Main) {
                        inputUsername.setError("Username tidak sesuai !")
                        inputPassword.setError("Password tidak sesuai !")
                        checkLogin = false

                    }
//                    return@OnClickListener
                } else {
                    Log.d("LoginActivity", "USER FOUND")
                    withContext(Dispatchers.Main) {
                        val moveHome = Intent(this@LoginActivity, MainPageActivity::class.java)
                        startActivity(moveHome)
                        checkLogin = true
                        sharePreference.setUser(user)

                    }
                }
            }
        })


//            if (username == checkUser && password == checkPass) checkLogin = true
//            if (username != "admin" && password != "kelompok9") {
//
//
//                if (username.isNotEmpty() && password.isNotEmpty()) {
//                    inputUsername.setError("Username salah!")
//                    inputPassword.setError("Password salah!")
//                    checkLogin = false
//                }
//                return@OnClickListener
//            }
            }

            fun getBundle() {
                val bundle: Bundle? = intent.extras
                val name: String? = bundle?.getString("admin")
                val pass: String? = bundle?.getString("kelompok9")

                inputUsername = findViewById(R.id.inputLayoutUsername)
                inputUsername.getEditText()?.setText(name)

                inputPassword = findViewById(R.id.inputLayoutPassword)
                inputPassword.getEditText()?.setText(pass)
            }


        }