package com.example.ugd1_a_kelompok9.destination

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ugd1_a_kelompok9.RClientDestination
import com.example.ugd1_a_kelompok9.Data.ResponseCreate
import com.example.ugd1_a_kelompok9.databinding.ActivityFormEditDestinationBinding
import com.shashank.sony.fancytoastlib.FancyToast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class FormEditDestinationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormEditDestinationBinding
    private var b:Bundle? = null
    private val listDestination = ArrayList<DestinationData>()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormEditDestinationBinding.inflate(layoutInflater)

        setContentView(binding.root)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Form Edit Destination"

        b = intent.extras
        val id = b?.getString("id")

        binding.tvTglBerangkat.setOnClickListener {
            val datePicker = DatePickerDialog.OnDateSetListener{
                    view,year,month,dayofMonth-> binding.tglberangkatView.text = dateToString(year,month,dayofMonth)
            }
            dateDialog(this,datePicker).show()
        }
        binding.tvTglPulang.setOnClickListener {
            val datePicker = DatePickerDialog.OnDateSetListener{
                    view,year,month,dayofMonth-> binding.tglpulangView.text = dateToString(year,month,dayofMonth)
            }
            dateDialog(this,datePicker).show()
        }

        id?.let { getDetailData(it) }

        binding.btnUpdate.setOnClickListener{
            with(binding){
                val nama= txtNama.text.toString()
                val tglBerangkat = tglberangkatView.text.toString()
                val tglPulang = tglpulangView.text.toString()
                val harga = txtHarga.text.toString()
                val deskripsi = txtDeskripsi.text.toString()

                RClientDestination.instances.updateData(id, nama,tglBerangkat, tglPulang,harga,deskripsi).enqueue(object :
                    Callback<ResponseCreate> {
                    override fun onResponse(call: Call<ResponseCreate>, response: Response<ResponseCreate>){
                        if (response.isSuccessful){
                            FancyToast.makeText(applicationContext,"${response.body()?.pesan}",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show()
                            finish()
                        } else {
                            val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                            FancyToast.makeText(applicationContext,jsonObj.getString("message"),
                                FancyToast.LENGTH_LONG,
                                FancyToast.ERROR,true).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseCreate>, t: Throwable) {

                    }
                })
            }
        }
    }

    fun getDetailData(nim:String){
        RClientDestination.instances.getData(nim).enqueue(object : Callback<ResponseDataDestination> {
            override fun onResponse(
                call: Call<ResponseDataDestination>,
                response: Response<ResponseDataDestination>
            ) {
                if (response.isSuccessful){
                    response.body()?.let{listDestination.addAll(it.data)}
                    with(binding){
                        txtNama.setText(listDestination[0].nama)
                        tglberangkatView.setText(listDestination[0].tanggal_berangkat)
                        tglpulangView.setText(listDestination[0].tanggal_pulang)
                        txtHarga.setText(listDestination[0].harga)
                        txtDeskripsi.setText(listDestination[0].deskripsi)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDataDestination>, t: Throwable) {

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