package com.example.ugd1_a_kelompok9.destination

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ugd1_a_kelompok9.RClientDestination
import com.example.ugd1_a_kelompok9.ResponseCreate
import com.example.ugd1_a_kelompok9.databinding.ActivityFormAddDestinationBinding
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormAddDestinationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormAddDestinationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormAddDestinationBinding.inflate(layoutInflater)
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
        binding.tvTglPulang.setOnClickListener {
            val datePicker = DatePickerDialog.OnDateSetListener{
                    view,year,month,dayofMonth-> binding.tglpulangView.text = dateToString(year,month,dayofMonth)
            }
            dateDialog(this,datePicker).show()
        }
    }

    fun saveData(){
        with(binding){
            val nama= txtNama.text.toString()
            val tglBerangkat = tglberangkatView.text.toString()
            val tglPulang = tglpulangView.text.toString()
            val harga = txtHarga.text.toString()
            val deskripsi = txtDeskripsi.text.toString()

            RClientDestination.instances.createData(nama,tglBerangkat, tglPulang,harga,deskripsi).enqueue(object :
                Callback<ResponseCreate> {
                override fun onResponse(
                    call: Call<ResponseCreate>,
                    response: Response<ResponseCreate>
                ) {
                    if(response.isSuccessful){
                        Toast.makeText(applicationContext,"${response.body()?.pesan}",Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                        txtNama.setError(jsonObj.getString("message"))
                        Toast.makeText(applicationContext,"Maaf sudah ada data", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ResponseCreate>, t: Throwable) {
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