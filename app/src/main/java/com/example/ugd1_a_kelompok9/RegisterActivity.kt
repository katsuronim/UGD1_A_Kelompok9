package com.example.ugd1_a_kelompok9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {
    private lateinit var email: TextInputEditText
    private lateinit var vEmail: TextInputLayout
    private lateinit var noTelp: TextInputEditText
    private lateinit var vnoTelp: TextInputLayout
    private lateinit var tanggalLahir: TextInputEditText
    private lateinit var vTglLahir: TextInputLayout
    private lateinit var username: TextInputEditText
    private lateinit var vUsername: TextInputLayout
    private lateinit var password: TextInputEditText
    private lateinit var vPassword: TextInputLayout
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        email = findViewById(R.id.inputEmail)
        vEmail = findViewById(R.id.regEmail)
        noTelp = findViewById(R.id.inputNoTelp)
        vnoTelp = findViewById(R.id.regNoTelp)
        tanggalLahir = findViewById(R.id.inputTanggalLahir)
        vTglLahir = findViewById(R.id.regTanggalLahir)
        username = findViewById(R.id.inputUsername)
        vUsername = findViewById(R.id.regUsername)
        password = findViewById(R.id.inputPassword)
        vPassword = findViewById(R.id.regPassword)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener{
            var checkRegister = false
            val intent = Intent(this, LoginActivity::class.java)
            val mBundle = Bundle()

            val LayUsername : String = vUsername.getEditText()?.getText().toString()
            val LayPassword : String = vPassword.getEditText()?.getText().toString()
            val LayNoTelp : String = vnoTelp.getEditText()?.getText().toString()
            val LayTgl : String = vTglLahir.getEditText()?.getText().toString()
            val LayEmail : String = vEmail.getEditText()?.getText().toString()

            if (LayUsername.isEmpty()) {
                username.setError("Nama Tidak Boleh Kosong")
            }
            if (LayPassword.isEmpty()) {
                password.setError("Password Tidak Boleh Kosong")
            }
            if (LayNoTelp.isEmpty()) {
                noTelp.setError("Nomor Telepon Tidak Boleh Kosong")
            }
            if (LayTgl.isEmpty()) {
                tanggalLahir.setError("Tanggal Lahir Tidak Boleh Kosong")
            }
            if (LayEmail.isEmpty()) {
                email.setError("Email Tidak Boleh Kosong")
            } else {
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
}