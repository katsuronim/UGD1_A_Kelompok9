
package com.example.ugd1_a_kelompok9.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ugd1_a_kelompok9.Data.RClient
import com.example.ugd1_a_kelompok9.Data.ResponseCreate
import com.example.ugd1_a_kelompok9.Data.ResponseDataUser
import com.example.ugd1_a_kelompok9.Data.UserData
import com.example.ugd1_a_kelompok9.databinding.ActivityFormEditUserBinding
import com.shashank.sony.fancytoastlib.FancyToast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FormEditUserActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormEditUserBinding
    private var vUsername: String = ""
    private var vPassword: String = ""
    private var vId: Int = 0
    private val listUser = ArrayList<UserData>()
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityFormEditUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Form Edit User"
        val intent = intent
//
//        val extras = intent.extras

        vUsername = intent.getStringExtra("username").toString()
        vPassword = intent.getStringExtra("password").toString()
        getDetailData(vUsername)

        binding.btnUpdate.setOnClickListener {
            with(binding) {
                val nama = txtEditNama.text.toString()
                val username = txtEditUsername.text.toString()
                val password = txtEditPassword.text.toString()
                val notelp = txtEditNotelp.text.toString()
                val tgllahir = txtEditTgllahir.text.toString()
                val email = txtEditEmail.text.toString()

                RClient.instances.updateData(vId,nama,username,password,notelp,tgllahir,email).enqueue(object :
                    Callback<ResponseCreate> {
                    override fun onResponse(
                        call: Call<ResponseCreate>,
                        response: Response<ResponseCreate>
                    ) {
                        if(response.isSuccessful) {
                            FancyToast.makeText(applicationContext,"${response.body()?.pesan}",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show()
                            finish()
                        } else {
                            val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                            FancyToast.makeText(applicationContext,jsonObj.getString("message"),
                                FancyToast.LENGTH_LONG,
                                FancyToast.ERROR,true).show()
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
                        vId = listUser[0].id
                        binding.txtEditNama.setText(listUser[0].nama)
                        binding.txtEditUsername.setText(listUser[0].username)
                        binding.txtEditPassword.setText(listUser[0].password)
                        binding.txtEditNotelp.setText(listUser[0].notelp)
                        binding.txtEditTgllahir.setText(listUser[0].tgllhr)
                        binding.txtEditEmail.setText(listUser[0].email)
                    }
                }}
            override fun onFailure(call: Call<ResponseDataUser>, t: Throwable) {
            }
        })
    }
}