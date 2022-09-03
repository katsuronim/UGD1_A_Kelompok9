package com.example.ugd1_a_kelompok9

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ugd1_a_kelompok9.entity.Creator

class FragmentCreator : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_creator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        val adapter : RVCreatorAdapter = RVCreatorAdapter(Creator.listOfCreator)

        val rvCreator : RecyclerView = view.findViewById(R.id.rv_creator)

        rvCreator.layoutManager = layoutManager

        rvCreator.setHasFixedSize(true)

        rvCreator.adapter = adapter
    }
}