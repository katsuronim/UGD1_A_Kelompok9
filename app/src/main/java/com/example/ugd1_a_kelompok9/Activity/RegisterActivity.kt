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
//    private lateinit var email: TextInputEditText
//    private lateinit var vEmail: TextInputLayout
//    private lateinit var noTelp: TextInputEditText
//    private lateinit var vnoTelp: TextInputLayout
//    private lateinit var tanggalLahir: TextInputEditText
//    private lateinit var vTglLahir: TextInputLayout
//    private lateinit var username: TextInputEditText
//    private lateinit var vUsername: TextInputLayout
//    private lateinit var password: TextInputEditText
//    private lateinit var vPassword: TextInputLayout
//    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(binding.root!!)

        createNotificationChannel()

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


//            if(inputNama.text.toString() == ""){
//                inputNama.setError("Nama Tidak Boleh Kosong")
//            }
//            if(inputUsername.text.toString() == ""){
//                inputUsername.setError("Username Tidak Boleh Kosong")
//            }
//            if(inputPassword.text.toString() == "") {
//                inputPassword.setError("Password Tidak Boleh Kosong")
//            }
//            if(inputEmail.text.toString() == ""){
//                inputEmail.setError("Email Tidak Boleh Kosong")
//            }
//            if(inputNoTelp.text.toString() == ""){
//                inputNoTelp.setError("No Telp Tidak Boleh Kosong")
//            }
//            if(inputTanggalLahir.text.toString() == ""){
//                inputTanggalLahir.setError("Tanggal Lahir Tidak Boleh Kosong")
//            } else {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_register)
//
//        email = findViewById(R.id.inputEmail)
//        vEmail = findViewById(R.id.regEmail)
//        noTelp = findViewById(R.id.inputNoTelp)
//        vnoTelp = findViewById(R.id.regNoTelp)
//        tanggalLahir = findViewById(R.id.inputTanggalLahir)
//        vTglLahir = findViewById(R.id.regTanggalLahir)
//        username = findViewById(R.id.inputUsername)
//        vUsername = findViewById(R.id.regUsername)
//        password = findViewById(R.id.inputPassword)
//        vPassword = findViewById(R.id.regPassword)
//        btnRegister = findViewById(R.id.btnRegister)
//
//        btnRegister.setOnClickListener{
//            var checkRegister = false
//            val intent = Intent(this, LoginActivity::class.java)
//            val mBundle = Bundle()
//
//            val LayUsername : String = vUsername.getEditText()?.getText().toString()
//            val LayPassword : String = vPassword.getEditText()?.getText().toString()
//            val LayNoTelp : String = vnoTelp.getEditText()?.getText().toString()
//            val LayTgl : String = vTglLahir.getEditText()?.getText().toString()
//            val LayEmail : String = vEmail.getEditText()?.getText().toString()
//
//            if (LayUsername.isEmpty()) {
//                username.setError("Nama Tidak Boleh Kosong")
//            }
//            if (LayPassword.isEmpty()) {
//                password.setError("Password Tidak Boleh Kosong")
//            }
//            if (LayNoTelp.isEmpty()) {
//                noTelp.setError("Nomor Telepon Tidak Boleh Kosong")
//            }
//            if (LayTgl.isEmpty()) {
//                tanggalLahir.setError("Tanggal Lahir Tidak Boleh Kosong")
//            }
//            if (LayEmail.isEmpty()) {
//                email.setError("Email Tidak Boleh Kosong")
//            } else {
//                //memasukkann data ke dalam bundle
//                mBundle.putString("email", email.text.toString())
//                mBundle.putString("noTelp", noTelp.text.toString())
//                mBundle.putString("tanggalLahir", tanggalLahir.text.toString())
//                mBundle.putString("username", username.text.toString())
//                mBundle.putString("password", password.text.toString())
//                //melakukan intent dengan memanggil bundle
//
//                intent.putExtras(mBundle)
//
//                startActivity(intent)
//            }
//
//        }
//    }
//}