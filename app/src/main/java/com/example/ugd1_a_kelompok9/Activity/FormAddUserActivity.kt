package com.example.ugd1_a_kelompok9.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ugd1_a_kelompok9.Data.RClient
import com.example.ugd1_a_kelompok9.Data.ResponseCreate
import com.example.ugd1_a_kelompok9.databinding.ActivityFormAddUserBinding
import com.shashank.sony.fancytoastlib.FancyToast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormAddUserActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormAddUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAdd.setOnClickListener {
            saveData()
        }
    }
    fun saveData(){
        with(binding) {
//            val id = txtId.text.toString()
            val nama= txtNama.text.toString()
            val username = txtUsername.text.toString()
            val password = txtPassword.text.toString()
            val notelp = txtNotelp.text.toString()
            val tgllahir = txtTgllahir.text.toString()
            val email = txtEmail.text.toString()

            RClient.instances.createData(nama,username,password,notelp, tgllahir, email).enqueue(object :
                Callback<ResponseCreate> {
                override fun onResponse(
                    call: Call<ResponseCreate>,
                    response: Response<ResponseCreate>
                ) {
                    if(response.isSuccessful){
                        FancyToast.makeText(applicationContext,"${response.body()?.pesan}",
                            FancyToast.LENGTH_LONG,
                            FancyToast.SUCCESS,true).show()
                        finish()
                    }else {
                        val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())

                        txtUsername.setError(jsonObj.getString("message"))
                        FancyToast.makeText(applicationContext,"Maaf sudah ada datanya",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show()
                    }
                }
                override fun onFailure(call:
                                       Call<ResponseCreate>, t: Throwable) {}
            })
        }
    }

}