package com.example.ugd1_a_kelompok9.destination

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ugd1_a_kelompok9.DestinationAdapter
import com.example.ugd1_a_kelompok9.RClientDestination
import com.example.ugd1_a_kelompok9.databinding.FragmentDataDestinationBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class FragmentDataDestination : Fragment() {
    private var _binding: FragmentDataDestinationBinding? = null
    private val binding get() = _binding!!
    private val listDestination = ArrayList<DestinationData>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataDestinationBinding.inflate(inflater, container, false)
        return binding.root
        getDataDestination()
    }
    override fun onStart() {
        super.onStart()
        getDataDestination()
    }
    private fun getDataDestination() {
        binding.rvData.setHasFixedSize(true)
        binding.rvData.layoutManager= LinearLayoutManager(context)
        val bundle = arguments
        val cari = bundle?.getString(/* key = */ "cari")
        binding.progressBar.visibility
        RClientDestination.instances.getData(cari).enqueue(object :
            Callback<ResponseDataDestination> {
            override fun onResponse(
                call: Call<ResponseDataDestination>,
                response: Response<ResponseDataDestination>
            ){
                if (response.isSuccessful){
                    listDestination.clear()
                    response.body()?.let {
                        listDestination.addAll(it.data) }
                    val adapter =
                        DestinationAdapter(listDestination, requireContext() )
                    binding.rvData.adapter = adapter
                    adapter.notifyDataSetChanged()
                    binding.progressBar.isVisible = false
                }
            }
            override fun onFailure(call: Call<ResponseDataDestination>, t:
            Throwable) {
            }
        }
        )
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}