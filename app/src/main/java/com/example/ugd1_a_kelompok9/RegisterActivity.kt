package com.example.ugd1_a_kelompok9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {
    private lateinit var email: TextInputEditText
    private lateinit var noTelp: TextInputEditText
    private lateinit var tanggalLahir: TextInputEditText
    private lateinit var username: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        email = findViewById(R.id.inputEmail)
        noTelp = findViewById(R.id.inputNoTelp)
        tanggalLahir = findViewById(R.id.inputTanggalLahir)
        username = findViewById(R.id.inputUsername)
        password = findViewById(R.id.inputPassword)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            val mBundle = Bundle()
            //memasukkann data ke dalam bundle
            mBundle.putString("email", email.text.toString())
            mBundle.putString("noTelp", noTelp.text.toString())
            mBundle.putString("tanggalLahir", tanggalLahir.text.toString())
            mBundle.putString("username", username.text.toString())
            mBundle.putString("password", password.text.toString())
            //melakukan intent dengan memanggil bundle
            intent.putExtras(mBundle)

            startActivity(intent)
        }
    }
}