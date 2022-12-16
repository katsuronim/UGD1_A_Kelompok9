package com.example.ugd1_a_kelompok9.Transportation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ugd1_a_kelompok9.Activity.DetailTransportationActivity
import com.example.ugd1_a_kelompok9.databinding.ListDataTransportationBinding
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator

class TransportationAdapter (
    private val listTransportation:ArrayList<TransportationData>,
    private val context: Context
    ): RecyclerView.Adapter<TransportationAdapter.TransportationViewHolder>() { inner class
    TransportationViewHolder(item: ListDataTransportationBinding):RecyclerView.ViewHolder(item.root){
        private val binding = item
        fun bind(transportationData: TransportationData){
            with (binding) {
                txtNama.text = transportationData.nama
                txtJenis.text = transportationData.jenis
                txtTanggalBerangkat.text = transportationData.tgl_berangkat
                cvData.setOnClickListener {
                    var i = Intent(context,
                        DetailTransportationActivity::class.java).apply {
                        putExtra("id",transportationData.id.toString())
                    }
                    context.startActivity(i)
                }
            }
        }
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType:
        Int): TransportationViewHolder {
            return TransportationViewHolder(
                ListDataTransportationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false
            ))
        }
        override fun onBindViewHolder(holder: TransportationViewHolder, position: Int) { holder.bind(listTransportation[position])
        }
        override fun getItemCount(): Int = listTransportation.size

}