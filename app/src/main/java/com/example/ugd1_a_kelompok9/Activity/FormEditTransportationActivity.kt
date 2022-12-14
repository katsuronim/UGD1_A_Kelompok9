package com.example.ugd1_a_kelompok9.Activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ugd1_a_kelompok9.Transportation.RClientTransportation
import com.example.ugd1_a_kelompok9.Transportation.ResponseCreateTransportation
import com.example.ugd1_a_kelompok9.Transportation.ResponseDataTransportation
import com.example.ugd1_a_kelompok9.Transportation.TransportationData
import com.example.ugd1_a_kelompok9.databinding.ActivityFormEditTransportationBinding
import com.shashank.sony.fancytoastlib.FancyToast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormEditTransportationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormEditTransportationBinding
    private var b:Bundle? = null
    private var idTransportation: Int = 0
    private val listTransportation = ArrayList<TransportationData>()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormEditTransportationBinding.inflate(layoutInflater)

        setContentView(binding.root)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Form Edit Transportation"

        b = intent.extras
        val id = b?.getString("id")

        binding.tvTglBerangkat.setOnClickListener {
            val datePicker = DatePickerDialog.OnDateSetListener{
                    view,year,month,dayofMonth-> binding.tglberangkatView.text = dateToString(year,month,dayofMonth)
            }
            dateDialog(this,datePicker).show()
        }

        id?.toString().let {
            if (it != null) {
                getDetailData(it)
            }
        }

        binding.btnUpdate.setOnClickListener{
            with(binding){
                val nama= txtNama.text.toString()
                val jenis= txtJenis.text.toString()
                val jamBerangkat = txtJamBerangkat.text.toString()
                val jamTiba = txtJamTiba.text.toString()
                val tglBerangkat = tglberangkatView.text.toString()
                val harga = txtHarga.text.toString()

                RClientTransportation.instances.updateData(idTransportation,nama,jenis,jamBerangkat,jamTiba,tglBerangkat,harga).enqueue(object :
                    Callback<ResponseCreateTransportation> {
                    override fun onResponse(call: Call<ResponseCreateTransportation>, response: Response<ResponseCreateTransportation>){
                        if (response.isSuccessful){
                            FancyToast.makeText(applicationContext,"${response.body()?.pesan}",
                                FancyToast.LENGTH_LONG,
                                FancyToast.SUCCESS,true).show()
                            finish()
                        } else {
                            val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                            FancyToast.makeText(applicationContext,jsonObj.getString("message"),
                                FancyToast.LENGTH_LONG,
                                FancyToast.ERROR,true).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseCreateTransportation>, t: Throwable) {

                    }
                })
            }
        }
    }

    fun getDetailData(nim:String){
        RClientTransportation.instances.getData(nim).enqueue(object :
            Callback<ResponseDataTransportation> {
            override fun onResponse(
                call: Call<ResponseDataTransportation>,
                response: Response<ResponseDataTransportation>
            ) {
                if (response.isSuccessful){
                    response.body()?.let{listTransportation.addAll(it.data)}
                    with(binding){
                        txtNama.setText(listTransportation[0].nama)
                        txtJenis.setText(listTransportation[0].jenis)
                        txtJamBerangkat.setText(listTransportation[0].jam_berangkat)
                        txtJamTiba.setText(listTransportation[0].jam_tiba)
                        tglberangkatView.setText(listTransportation[0].tgl_berangkat)
                        txtHarga.setText(listTransportation[0].harga)
                        idTransportation = listTransportation[0].id
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDataTransportation>, t: Throwable) {

            }
        })
    }

    private fun dateToString(year: Int, month: Int, dayofMonth:Int):String{
        return year.toString()+"-"+(month+1)+"-"+dayofMonth.toString()
    }

    private fun dateDialog(context: Context, datePicker: DatePickerDialog.OnDateSetListener): DatePickerDialog {
        val calender = Calendar.getInstance()
        return DatePickerDialog(
            context,datePicker,
            calender[Calendar.YEAR],
            calender[Calendar.MONTH],
            calender[Calendar.DAY_OF_MONTH],
        )
    }
}