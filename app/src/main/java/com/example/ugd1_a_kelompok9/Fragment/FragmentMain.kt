package com.example.ugd1_a_kelompok9.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ugd1_a_kelompok9.Activity.EditDestinationActivity
import com.example.ugd1_a_kelompok9.Activity.QRCodeActivity
import com.example.ugd1_a_kelompok9.Activity.ReadDestinationActivity
import com.example.ugd1_a_kelompok9.R
import com.example.ugd1_a_kelompok9.SharePreference
import com.example.ugd1_a_kelompok9.Transportation.ReadTransportationActivity
import com.example.ugd1_a_kelompok9.databinding.FragmentMainBinding
import com.example.ugd1_a_kelompok9.room.TourDB


class FragmentMain:Fragment(R.layout.fragment_main) {
    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var tourDb: TourDB
    private lateinit var sharePreference: SharePreference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)

        val rootView: View = inflater.inflate(R.layout.fragment_main, container, false)

        sharePreference = SharePreference(requireContext())

        binding.btnAdd.setOnClickListener {
            val intent = Intent(requireActivity(), ReadDestinationActivity::class.java)
            startActivity(intent)
//            CoroutineScope(Dispatchers.IO).launch {
//
//            }
        }
        binding.btnQRMenu.setOnClickListener{
            val intent = Intent(requireActivity(), QRCodeActivity::class.java)
            startActivity(intent)
        }
        binding.btnListTransportasi.setOnClickListener{
            val intent = Intent(requireActivity(), ReadTransportationActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
}