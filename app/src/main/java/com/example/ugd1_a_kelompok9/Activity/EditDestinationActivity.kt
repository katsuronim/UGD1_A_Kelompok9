package com.example.ugd1_a_kelompok9.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ugd1_a_kelompok9.R
import com.example.ugd1_a_kelompok9.room.Constant
import com.example.ugd1_a_kelompok9.room.Destination
import com.example.ugd1_a_kelompok9.room.DestinationDB
import kotlinx.android.synthetic.main.edit_destination.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditDestinationActivity : AppCompatActivity() {
    val db by lazy { DestinationDB(this) }
    private var destId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_destination)
        setupView()
        setupListener()
    }

    fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType){
            Constant.TYPE_CREATE -> {
                button_update.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                button_save.visibility = View.GONE
                button_update.visibility = View.GONE
                getDestination()
            }
            Constant.TYPE_UPDATE -> {
                button_save.visibility = View.GONE
                getDestination()
            }
        }
    }
    private fun setupListener() {
        button_save.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.destinationDao().addDestination(
                    Destination(0,edit_placeName.text.toString(),
                        edit_tglBerangkat.text.toString(),
                        edit_tglPulang.text.toString(),
                        edit_biaya.text.toString(),
                        edit_deskripsi.text.toString())
                )
                finish()
            }
        }
        button_update.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.destinationDao().updateDestination(
                    Destination(destId, edit_placeName.text.toString(),
                        edit_tglBerangkat.text.toString(),
                        edit_tglPulang.text.toString(),
                        edit_biaya.text.toString(),
                        edit_deskripsi.text.toString())
                )
                finish()
            }
        }
    }

    fun getDestination() {
        destId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val destinations = db.destinationDao().getDestination(destId)[0]
            edit_placeName.setText(destinations.placeName)
            edit_tglBerangkat.setText(destinations.tglBerangkat)
            edit_tglPulang.setText(destinations.tglPulang)
            edit_biaya.setText(destinations.cost)
            edit_deskripsi.setText(destinations.deskripsi)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}