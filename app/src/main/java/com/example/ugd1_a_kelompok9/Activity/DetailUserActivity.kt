package com.example.ugd1_a_kelompok9.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ugd1_a_kelompok9.Data.RClient
import com.example.ugd1_a_kelompok9.Data.ResponseCreate
import com.example.ugd1_a_kelompok9.Data.ResponseDataUser
import com.example.ugd1_a_kelompok9.Data.UserData
import com.example.ugd1_a_kelompok9.databinding.ActivityDetailUserBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailUserBinding
    private var b:Bundle? = null
    private val listUser = ArrayList<UserData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        b = intent.extras
        val idUser = b?.getString("id")
        idUser?.let { getDataDetail(it) }
        binding.btnHapus.setOnClickListener {
            idUser?.let { it1 -> deleteData(it1) }
        }
        binding.btnEdit.setOnClickListener {
            startActivity(
                Intent(this,
                    FormEditUserActivity::class.java).apply {
                    putExtra("id",idUser)
                })
        }
    }
    fun getDataDetail(idUser: String){
        RClient.instances.getData(idUser).enqueue(object : Callback<ResponseDataUser> {
            override fun onResponse(
                call: Call<ResponseDataUser>,
                response: Response<ResponseDataUser>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        listUser.addAll(it.data) }
                    with(binding) {
                        tvNama.text = listUser[0].nama
                        tvUsername.text = listUser[0].username
                        tvNotelp.text = listUser[0].notelp
                        tvTgllahir.text = listUser[0].tgllhr
                        tvEmail.text = listUser[0].email
                    }
                }
            }
            override fun onFailure(call: Call<ResponseDataUser>, t: Throwable) {
            }
        })
    }
    override fun onRestart() {
        super.onRestart()
        this.recreate()
    }
    fun deleteData(idUser:String){
        val builder = AlertDialog.Builder(this@DetailUserActivity)
        builder.setMessage("Anda Yakin mau hapus??")
            .setCancelable(false)
            .setPositiveButton("Ya!"){dialog, id->
                doDeleteData(idUser)
            }
            .setNegativeButton("Tidak!"){dialog,id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }
    private fun doDeleteData(id: String) {
        RClient.instances.deleteData(id).enqueue(object :
            Callback<ResponseCreate> {
            override fun onResponse(
                call: Call<ResponseCreate>,
                response: Response<ResponseCreate>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Data berhasil dihapus", Toast.LENGTH_LONG).show()
                    finish()
                }
            }
            override fun onFailure(call: Call<ResponseCreate>, t: Throwable) {
            }
        })
    }
}