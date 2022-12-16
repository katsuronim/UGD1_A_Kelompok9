package com.example.ugd1_a_kelompok9.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.ugd1_a_kelompok9.Activity.FormEditUserActivity
import com.example.ugd1_a_kelompok9.Activity.MainPageActivity
import com.example.ugd1_a_kelompok9.Data.RClient
import com.example.ugd1_a_kelompok9.Data.ResponseDataUser
import com.example.ugd1_a_kelompok9.Data.UserData
import com.example.ugd1_a_kelompok9.R
import com.example.ugd1_a_kelompok9.SharePreference
import com.example.ugd1_a_kelompok9.databinding.FragmentAkunBinding
import com.example.ugd1_a_kelompok9.room.UserDB
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentAkun : Fragment(R.layout.fragment_akun) {
    private var _binding : FragmentAkunBinding? = null
    private val binding get() = _binding!!

    private var b:Bundle? = null
    private val listUser = ArrayList<UserData>()
    private var email: String = ""
    private var password: String = ""

    private lateinit var userDb: UserDB
    private lateinit var sharePreference: SharePreference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ft = parentFragmentManager.beginTransaction()
        ft.detach(this).attach(this).commit()

        val activity: MainPageActivity? = activity as MainPageActivity?
        _binding = FragmentAkunBinding.inflate(inflater, container, false)
        email = activity!!.getEmail()
        password = activity!!.getPassword()

        binding.etEmail.setText(email)
        email?.let { getDataDetail(it) }

        binding.btnUpdate.setOnClickListener {
            val intent = Intent(requireActivity(), FormEditUserActivity::class.java)
            intent.putExtra("email", email)
            intent.putExtra("password", password)
            startActivity(intent)
        }
        return binding.root
    }

    fun getDataDetail(user:String){
        val email = b?.getString("email")
        email?.let { getDataDetail(it) }

        RClient.instances.getData(user).enqueue(object : Callback<ResponseDataUser> {
            override fun onResponse(
                call: Call<ResponseDataUser>,
                response: Response<ResponseDataUser>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        listUser.addAll(it.data) }
                    with(binding) {
                        binding.etNama.setText(listUser[0].nama)
                        binding.etUsername.setText(listUser[0].username)
                        binding.etEmail.setText(listUser[0].email)
                        binding.etTanggalLahir.setText(listUser[0].tgllhr)
                        binding.etNoTelp.setText(listUser[0].notelp)
                    }
                }
            }
            override fun onFailure(call: Call<ResponseDataUser>, t: Throwable) {
            }
        })
    }
}