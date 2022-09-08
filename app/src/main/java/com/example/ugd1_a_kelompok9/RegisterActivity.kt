package com.example.ugd1_a_kelompok9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.view.get
import androidx.core.view.isEmpty
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {
    private lateinit var regEmail: TextInputLayout
    private lateinit var regNoTelp: TextInputLayout
    private lateinit var regTanggalLahir: TextInputLayout
    private lateinit var regUsername: TextInputLayout
    private lateinit var regPassword: TextInputLayout
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        regEmail = findViewById(R.id.inputEmail)
        regNoTelp = findViewById(R.id.inputNoTelp)
        regTanggalLahir = findViewById(R.id.inputTanggalLahir)
        regUsername = findViewById(R.id.inputUsername)
        regPassword = findViewById(R.id.inputPassword)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val mBundle = Bundle()
            val username: String = regUsername.getEditText()?.getText().toString()
            val password: String = regPassword.getEditText()?.getText().toString()
            val email: String = regEmail.getEditText()?.getText().toString()
            val tanggalLahir: String = regTanggalLahir.getEditText()?.getText().toString()
            val noTelp: String = regNoTelp.getEditText()?.getText().toString()


            if (username.isEmpty()) {
                regUsername.setError("Nama Tidak Boleh Kosong")
            }

            if (password.isEmpty()) {
                regPassword.setError("Password Tidak Boleh Kosong")
            }

            if (noTelp.isEmpty()) {
                regNoTelp.setError("Nomor Telepon Tidak Boleh Kosong")
            }

            if (tanggalLahir.isEmpty()) {
                regTanggalLahir.setError("Tanggal Lahir Tidak Boleh Kosong")
            }

            if (email.isEmpty()) {
                regEmail.setError("Email Tidak Boleh Kosong")
            }


            //memasukkann data ke dalam bundle
            mBundle.putString("email", regEmail.getEditText()?.getText().toString())
            mBundle.putString("noTelp", regNoTelp.getEditText()?.getText().toString())
            mBundle.putString("tanggalLahir", regTanggalLahir.getEditText()?.getText().toString())
            mBundle.putString("username", regUsername.getEditText()?.getText().toString())
            mBundle.putString("password", regPassword.getEditText()?.getText().toString())
            //melakukan intent dengan memanggil bundle
            intent.putExtras(mBundle)
            startActivity(intent)
        }
    }
}