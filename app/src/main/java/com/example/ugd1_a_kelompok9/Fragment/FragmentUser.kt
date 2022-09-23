package com.example.ugd1_a_kelompok9.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ugd1_a_kelompok9.R
import com.example.ugd1_a_kelompok9.RecycleView.RVUserAdapter
import com.example.ugd1_a_kelompok9.room.User
import com.example.ugd1_a_kelompok9.room.UserDB


class FragmentUser : Fragment() {
    private lateinit var userDb: UserDB
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
       // val adapter : RVUserAdapter = RVUserAdapter(User.UserDB)

        val rvUser : RecyclerView = view.findViewById(R.id.rv_user)

        rvUser.layoutManager = layoutManager

        rvUser.setHasFixedSize(true)

        //rvUser.adapter = adapter
    }
}