package com.example.ugd1_a_kelompok9.Activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.ugd1_a_kelompok9.NotificationReceiver
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
    private val CHANNEL_ID_1 = "channel_notification_01"
    private val CHANNEL_ID_2 = "channel_notification_02"
    private val CHANNEL_ID_3 = "channel_notification_03"
    private val notificationId1 = 101
    private val notificationId2 = 102
    private val notificationId3 = 103

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createNotificationChannel()

        setContentView(R.layout.edit_destination)

        setupView()
        setupListener()

        createNotificationChannel()
    }

    fun setupView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
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
            sendNotification3()
            sendNotification2()
            CoroutineScope(Dispatchers.IO).launch {
                db.destinationDao().addDestination(
                    Destination(
                        0, edit_placeName.text.toString(),
                        edit_tglBerangkat.text.toString(),
                        edit_tglPulang.text.toString(),
                        edit_biaya.text.toString(),
                        edit_deskripsi.text.toString()
                    )
                )
                finish()
            }
        }
        button_update.setOnClickListener {
            sendNotification1()
            CoroutineScope(Dispatchers.IO).launch {
                db.destinationDao().updateDestination(
                    Destination(
                        destId, edit_placeName.text.toString(),
                        edit_tglBerangkat.text.toString(),
                        edit_tglPulang.text.toString(),
                        edit_biaya.text.toString(),
                        edit_deskripsi.text.toString()
                    )
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

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification Title"
            val descriptionText = "Notification Description"

            val channel1 = NotificationChannel(
                CHANNEL_ID_1,
                name,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = descriptionText
            }
            val channel2 = NotificationChannel(
                CHANNEL_ID_2,
                name,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = descriptionText
            }
            val channel3 = NotificationChannel(
                CHANNEL_ID_3,
                name,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel1)
            notificationManager.createNotificationChannel(channel2)
        }
    }

    private fun sendNotification1(){

        val intent : Intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent : PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val broadcastIntent : Intent = Intent( this, NotificationReceiver::class.java)
        //broadcastIntent.putExtra("toastMessage", binding?.etMessage?.text.toString())
        val actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID_1)
            .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
            .setContentTitle("Edit Destinasi")
            .setContentText("Berhasil Edit Destinasi")
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setColor(Color.BLUE)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .addAction(R.mipmap.voyager_launcher_foreground, "Toast", actionIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(notificationId1, builder.build())
        }
    }


    private fun sendNotification2(){

        val intent : Intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent : PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val broadcastIntent : Intent = Intent( this, NotificationReceiver::class.java)
        //broadcastIntent.putExtra("toastMessage", binding?.etMessage?.text.toString())
        val actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID_3)
            .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
            .setContentTitle("Save Destinasi")
            .setContentText("Berhasil Save Destinasi")
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setColor(Color.BLUE)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .addAction(R.mipmap.voyager_launcher_foreground, "Toast", actionIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(notificationId1, builder.build())
        }
    }


    private fun sendNotification3(){

        val intent : Intent = Intent(this, ReadDestinationActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent : PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val broadcastIntent : Intent = Intent( this, NotificationReceiver::class.java)
        //broadcastIntent.putExtra("toastMessage", binding?.etMessage?.text.toString())
        val actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID_1)
            .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
            .setContentTitle("Save Destinasi!")
            .setContentText("Berhasil Menambahkan Destinasi")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText(getString(R.string.text_dummy))
                .setBigContentTitle("Destinasi Baru!")
                .setSummaryText("Berhasil Menambahkan Destinasi")
            )
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setColor(Color.BLUE)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .addAction(R.mipmap.voyager_launcher_foreground, "Toast", actionIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(notificationId3, builder.build())
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}