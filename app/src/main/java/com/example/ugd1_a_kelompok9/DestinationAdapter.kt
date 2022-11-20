package com.example.ugd1_a_kelompok9

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ugd1_a_kelompok9.destination.DestinationData
import com.example.ugd1_a_kelompok9.destination.DetailDestinationActivity
import com.example.ugd1_a_kelompok9.databinding.ListDataDestinationBinding
import kotlinx.android.synthetic.main.destination_adapter.view.*

class DestinationAdapter(
    private val listDestination: ArrayList<DestinationData>,
//    private val listener: OnAdapterListener,
    private val context: Context
    ) :
    RecyclerView.Adapter<DestinationAdapter.DestinationViewHolder>() {
    inner class DestinationViewHolder(item: ListDataDestinationBinding):RecyclerView.ViewHolder(item.root){
        private val binding = item
        fun bind(destinationData: DestinationData) {
            with(binding) {
                txtNama.text = destinationData.nama
                txtTanggalBerangkat.text = destinationData.tanggal_berangkat
                txtTanggalPulang.text = destinationData.tanggal_pulang
                cvData.setOnClickListener{
                    var i = Intent(context,
                    DetailDestinationActivity::class.java).apply {
                        putExtra("nama",destinationData.nama)
                    }
                    context.startActivity(i)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            DestinationViewHolder {
        return DestinationViewHolder(ListDataDestinationBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: DestinationViewHolder, position:
    Int) {
//        val destination = destinations[position]
//        holder.view.placeName.text = destination.placeName
//        holder.view.cost.text = destination.cost.toString()
//        holder.view.description.text = destination.deskripsi
//        holder.view.placeName.setOnClickListener{
//            listener.onClick(destination)
//        }
//        holder.view.icon_edit.setOnClickListener {
//            listener.onUpdate(destination)
//        }
//        holder.view.icon_delete.setOnClickListener {
//            listener.onDelete(destination)
//        }
//        holder.view.icon_map.setOnClickListener {
//            listener.onMap(destination)
//        }
        holder.bind(listDestination[position])
    }

    override fun getItemCount() = listDestination.size

//    inner class DestinationViewHolder( val view: View) :
//        RecyclerView.ViewHolder(view)
    @SuppressLint("NotifyDataSetChanged")

    fun setData(list: List<DestinationData>){
        listDestination.clear()
        listDestination.addAll(list)
        notifyDataSetChanged()
    }
    interface OnAdapterListener {
        fun onClick(destinationData: DestinationData)
        fun onUpdate(destinationData: DestinationData)
        fun onDelete(destinationData: DestinationData)
        fun onMap(destinationData: DestinationData)
    }
}