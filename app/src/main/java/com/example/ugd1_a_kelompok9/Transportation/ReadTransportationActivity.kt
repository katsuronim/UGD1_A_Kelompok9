package com.example.ugd1_a_kelompok9.Transportation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.example.ugd1_a_kelompok9.Activity.FormAddTransportationActivity
import com.example.ugd1_a_kelompok9.R
import com.example.ugd1_a_kelompok9.databinding.ActivityReadDestinationBinding
import com.example.ugd1_a_kelompok9.databinding.ActivityReadTransportationBinding
import com.example.ugd1_a_kelompok9.destination.FormAddDestinationActivity
import com.example.ugd1_a_kelompok9.destination.FragmentDataDestination

class ReadTransportationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityReadTransportationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadTransportationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showDataFragment()
        binding.txtCari.setOnKeyListener(View.OnKeyListener{ _,
                                                             keyCode, event->
            if(keyCode == KeyEvent.KEYCODE_ENTER && event.action
                == KeyEvent.ACTION_UP)
            {
                showDataFragment()
                return@OnKeyListener true
            }
            false
        })
        binding.btnAdd.setOnClickListener{
            startActivity(
                Intent(this,
                    FormAddTransportationActivity::class.java)
            )
        }
    }
    fun showDataFragment() {
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction =
            mFragmentManager.beginTransaction()
        val mFragment = FragmentDataTransportation()
        val textCari = binding.txtCari.text
        val mBundle = Bundle()
        mBundle.putString("cari", textCari.toString())
        mFragment.arguments = mBundle
        mFragmentTransaction.replace(R.id.fl_data, mFragment).commit()
    }
}