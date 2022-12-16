package com.example.ugd1_a_kelompok9.destination

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ugd1_a_kelompok9.Activity.LocationActivity
import com.example.ugd1_a_kelompok9.RClientDestination
import com.example.ugd1_a_kelompok9.Data.ResponseCreate
import com.example.ugd1_a_kelompok9.R
import com.example.ugd1_a_kelompok9.databinding.ActivityDetailDestinationBinding
import com.shashank.sony.fancytoastlib.FancyToast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_destination.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailDestinationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailDestinationBinding
    private var b:Bundle? = null
    private val listDestination = ArrayList<DestinationData>()
    private var id: String = ""
    private var foto: String = ""
    private lateinit var imageUrl: String
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDestinationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        b = intent.extras
        val nama = b?.getString("nama")

        nama?.let { getDataDetail(it) }

        imageUrl = "https://www.rentalmobilbali.net/wp-content/uploads/2020/06/Waktu-Termurah-Liburan-Ke-Bali.jpg"
        image = findViewById(R.id.imageView)
        Picasso.with(this@DetailDestinationActivity).load(imageUrl).into(image)

        binding.btnHapus.setOnClickListener {
            id?.let { it1 -> deleteData(it1) }
        }

        binding.btnEdit.setOnClickListener {
            startActivity(Intent(this, FormEditDestinationActivity::class.java).apply {
                putExtra("id",id)
            })
        }

        binding.btnMaps.setOnClickListener {
            startActivity(Intent(this, LocationActivity::class.java))
        }
    }

    fun getDataDetail(nim:String){
        RClientDestination.instances.getData(nim).enqueue(object : Callback<ResponseDataDestination> {
            override fun onResponse(
                call: Call<ResponseDataDestination>,
                response: Response<ResponseDataDestination>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        listDestination.addAll(it.data)
                    }
                    with(binding){
                        tvNama.text = listDestination[0].nama
                        tvTglBerangkat.text = listDestination[0].tanggal_berangkat
                        tvTglPulang.text = listDestination[0].tanggal_pulang
                        tvHarga.text = listDestination[0].harga
                        tvDeskripsi.text = listDestination[0].deskripsi
                        id = listDestination[0].id
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDataDestination>, t: Throwable) {

            }
        })
    }

    override fun onRestart() {
        super.onRestart()
        this.recreate()
    }

    fun deleteData(nim: String){
        val builder = AlertDialog.Builder(this@DetailDestinationActivity)
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
        RClientDestination.instances.deleteData(nim).enqueue(object : Callback<ResponseCreate> {
            override fun onResponse(
                call: Call<ResponseCreate>,
                response: Response<ResponseCreate>
            ) {
                if(response.isSuccessful){
                    FancyToast.makeText(applicationContext,"Data berhasil dihapus", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show()
                    finish()
                }
            }

            override fun onFailure(call: Call<ResponseCreate>, t: Throwable) {

            }
        })
    }
}