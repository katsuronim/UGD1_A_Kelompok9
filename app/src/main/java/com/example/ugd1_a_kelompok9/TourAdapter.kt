package com.example.ugd1_a_kelompok9

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ugd1_a_kelompok9.room.Tour
import kotlinx.android.synthetic.main.activity_tour_adapter.view.*

class TourAdapter (private val tours: ArrayList<Tour>, private val listener: OnAdapterListener) :

    RecyclerView.Adapter<TourAdapter.NoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_tour_adapter,parent, false)
        )
    }
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val tour = tours[position]
        holder.view.text_title.text = tour.judul
        holder.view.text_title.setOnClickListener{
            listener.onClick(tour)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(tour)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(tour)
        }
    }
    override fun getItemCount() = tours.size
    inner class NoteViewHolder( val view: View): RecyclerView.ViewHolder(view)
    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Tour>){
        tours.clear()
        tours.addAll(list)
        notifyDataSetChanged()
    }
    interface OnAdapterListener {
        fun onClick(tour: Tour)
        fun onUpdate(tour: Tour)
        fun onDelete(tour: Tour)
    }
}