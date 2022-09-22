package com.example.ugd1_a_kelompok9

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ugd1_a_kelompok9.room.User
import kotlinx.android.synthetic.main.activity_user_adapter.view.*

class UserAdapter (private val user: ArrayList<User>, private val listener: OnAdapterListener) :
    RecyclerView.Adapter<UserAdapter.NoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_user_adapter,parent, false)
        )
    }
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val user = user[position]
        holder.view.text_title.text = user.username
        holder.view.text_title.setOnClickListener{
            listener.onClick(user)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(user)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(user)
        }
    }
    override fun getItemCount() = user.size
    inner class NoteViewHolder( val view: View): RecyclerView.ViewHolder(view)
    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<User>){
        user.clear()
        user.addAll(list)
        notifyDataSetChanged()
    }
    interface OnAdapterListener {
        fun onClick(user: User)
        fun onUpdate(user: User)
        fun onDelete(user: User)
    }
}