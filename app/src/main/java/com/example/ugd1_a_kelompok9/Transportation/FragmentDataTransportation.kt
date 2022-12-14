package com.example.ugd1_a_kelompok9.Transportation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ugd1_a_kelompok9.databinding.FragmentDataTransportationBinding
import com.example.ugd1_a_kelompok9.destination.ResponseDataDestination
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentDataTransportation : Fragment() {
    private var _binding: FragmentDataTransportationBinding? = null
    private val binding get() = _binding!!
    private val listTransportation = ArrayList<TransportationData>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataTransportationBinding.inflate(inflater, container, false)
        return binding.root
        getDataTransportation()
    }
    override fun onStart() {
        super.onStart()
        getDataTransportation()
    }
    private fun getDataTransportation() {
        binding.rvData.setHasFixedSize(true)
        binding.rvData.layoutManager= LinearLayoutManager(context)
        val bundle = arguments
        val cari = bundle?.getString(/* key = */ "cari")
        binding.progressBar.visibility
        if (cari != null) {
            RClientTransportation.instances.getData(cari).enqueue(object :
                Callback<ResponseDataTransportation> {
                override fun onResponse(
                    call: Call<ResponseDataTransportation>,
                    response: Response<ResponseDataTransportation>
                ){
                    if (response.isSuccessful){
                        listTransportation.clear()
                        response.body()?.let {
                            listTransportation.addAll(it.data) }
                        val adapter =
                            TransportationAdapter(listTransportation, requireContext() )
                        binding.rvData.adapter = adapter
                        adapter.notifyDataSetChanged()
                        binding.progressBar.isVisible = false
                    }
                }
                override fun onFailure(call: Call<ResponseDataTransportation>, t:
                Throwable) {
                }
            }
            )
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}