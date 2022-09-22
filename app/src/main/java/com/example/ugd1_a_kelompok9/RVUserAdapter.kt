package com.example.ugd1_a_kelompok9

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ugd1_a_kelompok9.room.User
import kotlinx.android.synthetic.main.activity_user_adapter.view.*

class RVUserAdapter (private val user: ArrayList<User>) : RecyclerView.Adapter<RVUserAdapter.viewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_user, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int){
        val currentItem = user[position]
        holder.tvFullname.text = currentItem.nama
        holder.tvDetail.text = "${currentItem.email} - ${currentItem.username}"
    }

    override fun getItemCount(): Int {
        return user.size
    }

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvFullname: TextView = itemView.findViewById(R.id.tv_fullname)
        val tvDetail: TextView = itemView.findViewById(R.id.tv_details)
    }
}