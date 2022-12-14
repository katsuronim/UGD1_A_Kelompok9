package com.example.ugd1_a_kelompok9.Activity

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ugd1_a_kelompok9.Data.ResponseCreate
import com.example.ugd1_a_kelompok9.RClientDestination
import com.example.ugd1_a_kelompok9.Transportation.RClientTransportation
import com.example.ugd1_a_kelompok9.Transportation.ResponseCreateTransportation
import com.example.ugd1_a_kelompok9.databinding.ActivityFormAddTransportationBinding
import com.shashank.sony.fancytoastlib.FancyToast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormAddTransportationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormAddTransportationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormAddTransportationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener{
            saveData()
        }
        binding.tvTglBerangkat.setOnClickListener {
            val datePicker = DatePickerDialog.OnDateSetListener{
                    view,year,month,dayofMonth-> binding.tglberangkatView.text = dateToString(year,month,dayofMonth)
            }
            dateDialog(this,datePicker).show()
        }
    }

    fun saveData(){
        with(binding){
            val nama= txtNama.text.toString()
            val jenis= txtJenis.text.toString()
            val jamBerangkat = txtJamBerangkat.text.toString()
            val jamTiba = txtJamTiba.text.toString()
            val tglBerangkat = tglberangkatView.text.toString()
            val harga = txtHarga.text.toString()

            RClientTransportation.instances.createData(nama,jenis,jamBerangkat,jamTiba,tglBerangkat,harga).enqueue(object :
                Callback<ResponseCreateTransportation> {
                override fun onResponse(
                    call: Call<ResponseCreateTransportation>,
                    response: Response<ResponseCreateTransportation>
                ) {
                    if(response.isSuccessful){
                        FancyToast.makeText(applicationContext,"${response.body()?.pesan}",
                            FancyToast.LENGTH_LONG,
                            FancyToast.SUCCESS,true).show()
                        finish()
                    }else{
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