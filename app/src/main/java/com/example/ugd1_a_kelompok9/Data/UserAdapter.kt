package com.example.ugd1_a_kelompok9.Data

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ugd1_a_kelompok9.Activity.DetailUserActivity
import com.example.ugd1_a_kelompok9.Data.UserData
import com.example.ugd1_a_kelompok9.databinding.ListDataUserBinding

class UserAdapter (
    private val listUser:ArrayList<UserData>,
    private val context: Context
): RecyclerView.Adapter<UserAdapter.UserViewHolder>() { inner class
UserViewHolder(item: ListDataUserBinding):RecyclerView.ViewHolder(item.root){
    private val binding = item
    fun bind(userData: UserData){
        with (binding) {
            txtUsername.text = userData.username
            txtNama.text = userData.nama
            cvData.setOnClickListener {
                var i = Intent(context,
                    DetailUserActivity::class.java).apply {
                    putExtra("id",userData.id)
                }
                context.startActivity(i)
            }
        }
    }
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int): UserViewHolder {
        return UserViewHolder(ListDataUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        ))
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) { holder.bind(listUser[position])
    }
    override fun getItemCount(): Int = listUser.size
}
