package com.example.ugd1_a_kelompok9

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ugd1_a_kelompok9.room.Destination
import kotlinx.android.synthetic.main.destination_adapter.view.*

class DestinationAdapter(private val destinations: ArrayList<Destination>, private val listener: OnAdapterListener) :
    RecyclerView.Adapter<DestinationAdapter.DestinationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            DestinationViewHolder {
        return DestinationViewHolder(

            LayoutInflater.from(parent.context).inflate(R.layout.destination_adapter,parent, false)
        )
    }
    override fun onBindViewHolder(holder: DestinationViewHolder, position:
    Int) {
        val destination = destinations[position]
        holder.view.placeName.text = destination.placeName
        holder.view.placeName.setOnClickListener{
            listener.onClick(destination)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(destination)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(destination)
        }
    }

    override fun getItemCount() = destinations.size

    inner class DestinationViewHolder( val view: View) :
        RecyclerView.ViewHolder(view)
    @SuppressLint("NotifyDataSetChanged")

    fun setData(list: List<Destination>){
        destinations.clear()
        destinations.addAll(list)
        notifyDataSetChanged()
    }
    interface OnAdapterListener {
        fun onClick(destination: Destination)
        fun onUpdate(destination: Destination)
        fun onDelete(destination: Destination)
    }
}