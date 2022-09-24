package com.example.ugd1_a_kelompok9.Activity

import android.widget.Button
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.ugd1_a_kelompok9.R

class NextRegisterActivity : AppCompatActivity() {
    private lateinit var email: TextView
    private lateinit var nama: TextView
    private lateinit var username: TextView
    private lateinit var password: TextView
    private lateinit var mBundle: Bundle

    private lateinit var vEmail: String
    private lateinit var vNama: String
    private lateinit var vUsername: String
    private lateinit var vPassword: String

    val btnConfirm: Button = findViewById(R.id.btnConfirm)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next_register)

        getBundle()
        setText()

        btnConfirm.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    fun getBundle(){
        //mengambil bundle dari activity sebelumya dengan menggunakan key register
        mBundle = intent.getBundleExtra("register")!!
        //mengambil data dari bundle
        vEmail = mBundle.getString("email")!!
        vNama = mBundle.getString("nama")!!
        vUsername = mBundle.getString("username")!!
        vPassword = mBundle.getString("password")!!
    }

    fun setText() {
        email = findViewById(R.id.outEmail)
        email.setText(vEmail)
        nama = findViewById(R.id.outNama)
        nama.setText(vNama)
        username = findViewById(R.id.outUsername)
        username.setText(vUsername)
        password = findViewById(R.id.outPassword)
        password.setText(vPassword)
    }
    
}