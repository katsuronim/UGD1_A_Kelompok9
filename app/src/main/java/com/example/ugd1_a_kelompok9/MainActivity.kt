package com.example.ugd1_a_kelompok9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var inputUsername: TextInputLayout
    private lateinit var inputPassword: TextInputLayout
    private lateinit var mainLayout: ConstraintLayout
    lateinit var mBundle: Bundle
    lateinit var vUsername: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        getBundle()

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

            if (username == "admin" && password == "kelompok9") checkLogin = true
            if (username != "admin" && password != "kelompok9") {
                if(username.isEmpty()){
                    inputUsername.setError("Username must be filled with text")
                    checkLogin = false
                }
                if (password.isEmpty()){
                    inputPassword.setError("Password must be filled with text")
                    checkLogin = false
                }

                if (username.isNotEmpty() && password.isNotEmpty()) {
                    inputUsername.setError("Username salah!")
                    inputPassword.setError("Password salah!")
                    checkLogin = false
                }

                return@OnClickListener
            }
            val moveHome = Intent(this@MainActivity, MainPageActivity::class.java)
            startActivity(moveHome)
        })
    }

    fun getBundle(){
        val bundle: Bundle? = intent.extras
        val name: String? = bundle?.getString("username")

        inputUsername = findViewById(R.id.inputLayoutUsername)
        inputUsername.getEditText()?.setText(name)
    }
}