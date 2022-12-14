package com.example.ugd1_a_kelompok9.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.example.ugd1_a_kelompok9.R
import com.example.ugd1_a_kelompok9.Transportation.RClientTransportation
import com.example.ugd1_a_kelompok9.Transportation.ResponseCreateTransportation
import com.example.ugd1_a_kelompok9.Transportation.ResponseDataTransportation
import com.example.ugd1_a_kelompok9.Transportation.TransportationData
import com.example.ugd1_a_kelompok9.databinding.ActivityDetailTransportationBinding
import com.shashank.sony.fancytoastlib.FancyToast
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailTransportationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailTransportationBinding
    private var b:Bundle? = null
    private val listTransportation = ArrayList<TransportationData>()
    private var foto: String = ""
    private lateinit var imageUrl: String
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTransportationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        b = intent.extras
        val id = b?.getString("id")

        id?.let { getDataDetail(it) }

        imageUrl = "https://www.rentalmobilbali.net/wp-content/uploads/2020/06/Waktu-Termurah-Liburan-Ke-Bali.jpg"
        image = findViewById(R.id.imageView)
        Picasso.with(this@DetailTransportationActivity).load(imageUrl).into(image)

        binding.btnHapus.setOnClickListener {
            id?.let { it1 -> deleteData(it1) }
        }

        binding.btnEdit.setOnClickListener {
            startActivity(Intent(this, FormEditTransportationActivity::class.java).apply {
                putExtra("id",id)
            })
        }
    }

    fun getDataDetail(nim:String){
        RClientTransportation.instances.getData(nim).enqueue(object :
            Callback<ResponseDataTransportation> {
            override fun onResponse(
                call: Call<ResponseDataTransportation>,
                response: Response<ResponseDataTransportation>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        listTransportation.addAll(it.data)
                    }
                    with(binding){
                        tvNama.text = listTransportation[0].nama
                        tvJenis.text = listTransportation[0].jenis
                        tvJamBerangkat.text = listTransportation[0].jam_berangkat
                        tvJamTiba.text = listTransportation[0].jam_tiba
                        tvTglBerangkat.text = listTransportation[0].tgl_berangkat
                        tvHarga.text = listTransportation[0].harga
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDataTransportation>, t: Throwable) {

            }
        })
    }

    override fun onRestart() {
        super.onRestart()
        this.recreate()
    }

    fun deleteData(nim: String){
        val builder = AlertDialog.Builder(this@DetailTransportationActivity)
        builder.setMessage("Anda Yakin mau hapus ??")
            .setCancelable(false)
            .setPositiveButton("Ya, Hapus Aja!"){
                    dialog, id-> doDeleteData(nim)
            }
            .setNegativeButton("Tidak, Masih sayang dataku"){
                    dialog, id -> dialog.dismiss()
            }

        val alert = builder.create()
        alert.show()
    }

    private fun doDeleteData(nim: String){
        RClientTransportation.instances.deleteData(nim).enqueue(object : Callback<ResponseCreateTransportation> {
            override fun onResponse(
                call: Call<ResponseCreateTransportation>,
                response: Response<ResponseCreateTransportation>
            ) {
                if(response.isSuccessful){
                    FancyToast.makeText(applicationContext,"Data berhasil dihapus", FancyToast.LENGTH_LONG,
                        FancyToast.SUCCESS,true).show()
                    finish()
                }
            }

            override fun onFailure(call: Call<ResponseCreateTransportation>, t: Throwable) {

            }
        })
    }
}