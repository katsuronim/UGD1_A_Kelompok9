package com.example.ugd1_a_kelompok9

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ugd1_a_kelompok9.entity.User

class RVUserAdapter (private val data: Array<User>) : RecyclerView.Adapter<RVUserAdapter.viewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_user, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int){
        val currentItem = data[position]
        holder.tvUsername.text = currentItem.username
        holder.tvDetail.text = "${currentItem.email} - ${currentItem.password}"
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvUsername: TextView = itemView.findViewById(R.id.tv_username)
        val tvDetail: TextView = itemView.findViewById(R.id.tv_details)
    }
}