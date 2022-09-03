package com.example.ugd1_a_kelompok9

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ugd1_a_kelompok9.entity.Creator

class RVCreatorAdapter (private val data: Array<Creator>) : RecyclerView.Adapter<RVCreatorAdapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_creator, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentItem = data[position]
        holder.tvNamaCreator.text = currentItem.nama
        holder.tvDetailsCreator.text = currentItem.npm
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNamaCreator: TextView = itemView.findViewById(R.id.tv_nama_creator)
        val tvDetailsCreator: TextView = itemView.findViewById(R.id.tv_details_creator)
    }
}