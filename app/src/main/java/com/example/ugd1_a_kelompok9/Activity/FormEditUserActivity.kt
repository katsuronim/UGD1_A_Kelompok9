
package com.example.ugd1_a_kelompok9.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ugd1_a_kelompok9.Data.RClient
import com.example.ugd1_a_kelompok9.Data.ResponseCreate
import com.example.ugd1_a_kelompok9.Data.ResponseDataUser
import com.example.ugd1_a_kelompok9.Data.UserData
import com.example.ugd1_a_kelompok9.databinding.ActivityFormEditUserBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class FormEditUserActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormEditUserBinding
    private var b:Bundle? = null
    private val listUser = ArrayList<UserData>()
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityFormEditUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Form Edit User"
        b = intent.extras
        val idUser = b?.getString("id")

        binding.btnUpdate.setOnClickListener {
            with(binding) {
                val nama = txtEditNama.text.toString()
                val username = txtEditUsername.text.toString()
                val password = txtEditPassword.text.toString()
                val notelp = txtEditNotelp.text.toString()
                val tgllahir = txtEditTgllahir.text.toString()
                val email = txtEditEmail.text.toString()

                RClient.instances.updateData(idUser,nama,username,password,notelp,tgllahir,email).enqueue(object :
                    Callback<ResponseCreate> {
                    override fun onResponse(
                        call: Call<ResponseCreate>,
                        response: Response<ResponseCreate>
                    ) {
                        if(response.isSuccessful) {
                            Toast.makeText(applicationContext,"${response.body()?.pesan}",
                                Toast.LENGTH_LONG).show()
                            finish()
                        }
                    }
                    override fun onFailure(call:
                                           Call<ResponseCreate>, t: Throwable) {
                    }
                })
            }
        }
    }
    fun getDetailData(idUser: String) {
        RClient.instances.getData(idUser).enqueue(object : Callback<ResponseDataUser> {
            override fun onResponse(
                call: Call<ResponseDataUser>,
                response: Response<ResponseDataUser>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        listUser.addAll(it.data) }
                    with(binding) {
//                        txtNobp.setText(listUser[0].nim)

                        txtEditNama.setText(listUser[0].nama)

                        txtEditUsername.setText(listUser[0].username)

                        txtEditPassword.setText(listUser[0].password)

                        txtEditNotelp.setText(listUser[0].notelp)

                        txtEditTgllahir.setText(listUser[0].tgllhr)

                        txtEditEmail.setText(listUser[0].email)
                    }
                }}
            override fun onFailure(call: Call<ResponseDataUser>, t: Throwable) {
            }
        })
    }
}