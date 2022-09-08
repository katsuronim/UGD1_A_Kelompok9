package com.example.ugd1_a_kelompok9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.view.isEmpty
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {
    private lateinit var email: TextInputLayout
    private lateinit var noTelp: TextInputLayout
    private lateinit var tanggalLahir: TextInputLayout
    private lateinit var username: TextInputLayout
    private lateinit var password: TextInputLayout
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

        btnRegister.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val mBundle = Bundle()


            if (username.isEmpty()) {
                username.setError("Nama Tidak Boleh Kosong")
                if (password.isEmpty()) {
                    password.setError("Password Tidak Boleh Kosong")
                    if (noTelp.isEmpty()) {
                        noTelp.setError("Nomor Telepon Tidak Boleh Kosong")
                        if (tanggalLahir.isEmpty()) {
                            tanggalLahir.setError("Tanggal Lahir Tidak Boleh Kosong")
                            if (email.isEmpty()) {
                                email.setError("Email Tidak Boleh Kosong")


                                //memasukkann data ke dalam bundle
                                mBundle.putString("email", email.getEditText()?.getText().toString())
                                mBundle.putString("noTelp", noTelp.getEditText()?.getText().toString())
                                mBundle.putString("tanggalLahir", tanggalLahir.getEditText()?.getText().toString())
                                mBundle.putString("username", username.getEditText()?.getText().toString())
                                mBundle.putString("password", password.getEditText()?.getText().toString())
                                //melakukan intent dengan memanggil bundle
                                intent.putExtras(mBundle)
                                startActivity(intent)
                            }
                        }
                    }
                }
            }
        }
    }
}