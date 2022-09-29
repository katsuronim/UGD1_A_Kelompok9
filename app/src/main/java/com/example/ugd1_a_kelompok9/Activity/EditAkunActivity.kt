package com.example.ugd1_a_kelompok9.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.room.Room
import com.example.ugd1_a_kelompok9.Activity.LoginActivity
import com.example.ugd1_a_kelompok9.Activity.MainPageActivity
import com.example.ugd1_a_kelompok9.SharePreference
import com.example.ugd1_a_kelompok9.databinding.ActivityEditAkunBinding
import com.example.ugd1_a_kelompok9.room.User
import com.example.ugd1_a_kelompok9.room.UserDB
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditAkunActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditAkunBinding
    private lateinit var db: UserDB
    private lateinit var _currentUser: User
    lateinit var sharedPreferences: SharedPreferences
    lateinit var sharePreference: SharePreference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAkunBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(applicationContext, UserDB::class.java, "appUser.db").build()

        sharePreference = SharePreference(applicationContext)
        sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)

        //getUser()

        val id = sharePreference.getUser()?.userID.toString()
        val userId = sharePreference.getUser()?.userID
        val nama = sharePreference.getUser()?.name
        val username = sharePreference.getUser()?.username
        val password = sharePreference.getUser()?.password
        val email = sharePreference.getUser()?.email
        val noTelp = sharePreference.getUser()?.noTelp
        val tanggalLahir = sharePreference.getUser()?.tanggalLahir

        binding.etNama.setText(nama)
        binding.etUsername.setText(username)
        binding.etTanggalLahir.setText(tanggalLahir)
        binding.etNoTelp.setText(noTelp)
        binding.etEmail.setText(email)

        //val id : Int? = _currentUser.userID

        binding!!.btnSimpan.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val logged : User = db.userDao().getUserID(userId)
                db.userDao().updateUser(
                    logged.userID,
                    binding!!.etNama.text.toString(),
                    binding!!.etUsername.text.toString(),
                    logged.password,
                    binding!!.etTanggalLahir.text.toString(),
                    binding!!.etNoTelp.text.toString(),
                    binding!!.etEmail.text.toString()
                )

                //Toast.makeText(applicationContext, "Berhasil Update! Silahkan login ulang!", Toast.LENGTH_LONG).show()

                val intent = Intent(this@EditAkunActivity, LoginActivity::class.java)
                startActivity(intent)
//                sharePreference.setUser(User(
//                    logged.userID,
//                    binding!!.LayoutNama.getEditText().toString(),
//                    binding!!.LayoutUsername.getEditText().toString(),
//                    logged.password,
//                    binding!!.LayoutEmail.getEditText().toString(),
//                    binding!!.LayoutTelp.getEditText().toString(),
//                    binding!!.LayoutTanggalLahir.getEditText().toString()
//                ))
                finish()
            }
        }
        setContentView(binding?.root)
    }

    fun getUser(){

        //_currentUser = User(id,nama,username,password,email,noTelp,tanggalLahir)
    }
}