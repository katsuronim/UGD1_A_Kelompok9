package com.example.ugd1_a_kelompok9

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NotificationReceiver  : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent) {
        val message = intent.getStringExtra("toastMessage")
        Toast.makeText(context, "Proses Berhasil dilakukan", Toast.LENGTH_LONG).show()
    }
}