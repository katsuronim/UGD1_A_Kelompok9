package com.example.ugd1_a_kelompok9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {
    private lateinit var email: TextInputEditText
    private lateinit var nama: TextInputEditText
    private lateinit var username: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        email = findViewById(R.id.inputEmail)
        nama = findViewById(R.id.inputNama)
        username = findViewById(R.id.inputUsername)
        password = findViewById(R.id.inputPassword)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener{
            val intent = Intent(this, NextRegisterActivity::class.java)
            val mBundle = Bundle()
            //memasukkann data ke dalam bundle
            mBundle.putString("email", email.text.toString())
            mBundle.putString("nama", nama.text.toString())
            mBundle.putString("username", username.text.toString())
            mBundle.putString("password", password.text.toString())
            //melakukan intent dengan memanggil bundle
            intent.putExtra("register", mBundle)

            startActivity(intent)
        }
    }
}