package com.example.ugd1_a_kelompok9.Activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ugd1_a_kelompok9.room.Constant
import com.example.ugd1_a_kelompok9.room.Destination
import com.example.ugd1_a_kelompok9.room.DestinationDB
import com.example.ugd1_a_kelompok9.DestinationAdapter
import com.example.ugd1_a_kelompok9.R
import kotlinx.android.synthetic.main.activity_read_destination.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.ugd1_a_kelompok9.NotificationReceiver

class ReadDestinationActivity : AppCompatActivity() {
    val db by lazy { DestinationDB(this) }
    lateinit var destinationAdapter: DestinationAdapter
    private val CHANNEL_ID_1 = "channel_notification_01"
    private val notificationId1 = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_destination)
        setupListener()
        setupRecyclerView()

        createNotificationChannel()
    }
    //berfungsi untuk membuat sebuah note status pada button yang
    //ditekan untuk CRUD yang dilaksanakan
    //ini berhubungan dengan Constant status pada room
    //cara panggil id dengan memanggil fungsi intetnEdit.
    //jika pada fungsi interface adapterListener berubah, maka object
    //akan memerah error karena penambahan fungsi.
    private fun setupRecyclerView() {
        destinationAdapter = DestinationAdapter(arrayListOf(), object :
            DestinationAdapter.OnAdapterListener{
            override fun onClick(destination: Destination) {
                //Toast.makeText(applicationContext, note.title, Toast.LENGTH_SHORT).show()
                intentEdit(destination.destID,Constant.TYPE_READ)
            }
            override fun onUpdate(destination: Destination) {
                intentEdit(destination.destID, Constant.TYPE_UPDATE)
            }
            override fun onDelete(destination: Destination) {
                deleteDialog(destination)
            }
        })
        list_note.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = destinationAdapter
        }
    }
    private fun deleteDialog(destination: Destination){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Confirmation")
            setMessage("Are You Sure to delete this data From ${destination.placeName}?")
            setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
            })
            setPositiveButton("Delete", DialogInterface.OnClickListener
            { dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.destinationDao().deleteDestination(destination)
                    loadData()
                    sendNotification1()
                }
            })
        }
        alertDialog.show()
    }
    override fun onStart() {
        super.onStart()
        loadData()
    }
    //untuk load data yang tersimpan pada database yang sudah create data
    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            val destinations = db.destinationDao().getDestinations()
            Log.d("MainActivity","dbResponse: $destinations")
            withContext(Dispatchers.Main){
                destinationAdapter.setData( destinations )
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
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel1)
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
            .setContentTitle("Hapus Destinasi")
            .setContentText("Berhasil Hapus Destinasi")
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

    fun setupListener() {
        button_create.setOnClickListener{
            intentEdit(0,Constant.TYPE_CREATE)
        }
    }
    //pick data dari Id yang sebagai primary key
    fun intentEdit(destinationId : Int, intentType: Int){
        startActivity(
            Intent(applicationContext, EditDestinationActivity::class.java)
                .putExtra("intent_id", destinationId)
                .putExtra("intent_type", intentType))
    }
}


