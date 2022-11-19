package com.example.ugd1_a_kelompok9.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ugd1_a_kelompok9.Data.RClient
import com.example.ugd1_a_kelompok9.Data.ResponseDataUser
import com.example.ugd1_a_kelompok9.Data.UserAdapter
import com.example.ugd1_a_kelompok9.Data.UserData
import com.example.ugd1_a_kelompok9.databinding.FragmentDataUserBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class DataUserFragment : Fragment() {
    private var _binding: FragmentDataUserBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private val listUser = ArrayList<UserData>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataUserBinding.inflate(inflater,
            container, false)
        return binding.root
        getDataUser()
    }

    override fun onStart() {
        super.onStart()
        getDataUser()
    }

    private fun getDataUser() {
        binding.rvData.setHasFixedSize(true)
        binding.rvData.layoutManager = LinearLayoutManager(context)
        val bundle = arguments
        val cari = bundle?.getString(/* key = */ "cari")
        binding.progressBar.visibility

        if (cari != null) {
            RClient.instances.getData(cari).enqueue(object : Callback<ResponseDataUser> {
                override fun onResponse(
                    call: Call<ResponseDataUser>,
                    response: Response<ResponseDataUser>
                ){
                    if (response.isSuccessful){
                        listUser.clear()
                        response.body()?.let { listUser.addAll(it.data) }
                        val adapter = UserAdapter(listUser, requireContext())
                        binding.rvData.adapter = adapter
                        adapter.notifyDataSetChanged()
                        binding.progressBar.isVisible = false
                    }
                }
                override fun onFailure(call: Call<ResponseDataUser>, t: Throwable) {

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

