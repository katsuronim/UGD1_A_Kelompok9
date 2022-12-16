package com.example.ugd1_a_kelompok9.Activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.room.Room
import com.example.ugd1_a_kelompok9.NotificationReceiver
import com.example.ugd1_a_kelompok9.R
import com.example.ugd1_a_kelompok9.databinding.ActivityRegisterBinding
import com.example.ugd1_a_kelompok9.room.User
import com.example.ugd1_a_kelompok9.room.UserDB
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var db: UserDB
    private val CHANNEL_ID_1 = "channel_notification_01"
    private val CHANNEL_ID_2 = "channel_notification_02"
    private val notificationId1 = 101
    private val notificationId2 = 102
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(binding.root!!)

        createNotificationChannel()

        auth = FirebaseAuth.getInstance()

        db = Room.databaseBuilder(applicationContext, UserDB::class.java, "appUser.db").build()
        binding!!.btnRegister.setOnClickListener {
                sendNotification1()
                sendNotification2()
                CoroutineScope(Dispatchers.IO).launch {
                    db.userDao().addUser(
                        User(
                            0,
                            inputNama.text.toString(),
                            inputUsername.text.toString(),
                            inputPassword.text.toString(),
                            inputEmail.text.toString(),
                            inputNoTelp.text.toString(),
                            inputTanggalLahir.text.toString()
                        )
                    )
                    finish()
                }
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
                .setContentTitle("Berhasil Registrasi")
                .setContentText("Registrasi akun berhasil!")
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
//        val builder = NotificationCompat.Builder(this, CHANNEL_ID_1)
//            .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
//            .setContentTitle("Proses Registrasi")
//            .setContentText("Selamat anda berhasil registrasi!")
//            .setPriority(NotificationCompat.PRIORITY_LOW)

        val myBitmap = BitmapFactory.decodeResource(resources, R.drawable.travel)
        val builder = NotificationCompat.Builder(this, CHANNEL_ID_2)
            .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
            .setContentTitle("Proses Registrasi")
            .setContentText("Berhasil Registrasi")
            .setLargeIcon(myBitmap)
            .setStyle(NotificationCompat.BigPictureStyle()
                .bigPicture(myBitmap)
                .bigLargeIcon(myBitmap))

        with(NotificationManagerCompat.from(this)){
            notify(notificationId2, builder.build())
        }
    }
}