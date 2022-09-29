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

class ReadDestinationActivity : AppCompatActivity() {
    val db by lazy { DestinationDB(this) }
    lateinit var destinationAdapter: DestinationAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_destination)
        setupListener()
        setupRecyclerView()
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
        list_destination.apply {
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
