package com.example.ugd1_a_kelompok9.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ugd1_a_kelompok9.R
import com.example.ugd1_a_kelompok9.SharePreference
import com.example.ugd1_a_kelompok9.databinding.FragmentAkunBinding
import com.example.ugd1_a_kelompok9.room.User
import com.example.ugd1_a_kelompok9.room.UserDB
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentAkun : Fragment(R.layout.fragment_akun) {
    private var _binding : FragmentAkunBinding? = null
    private val binding get() = _binding!!

    private lateinit var userDb: UserDB
    private lateinit var sharePreference: SharePreference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentAkunBinding.inflate(inflater, container, false)

        val rootView: View = inflater.inflate(R.layout.fragment_akun, container, false)

        sharePreference = SharePreference(requireContext())
        userDb = UserDB.getDatabase(requireContext())
        //usersDb = Room.databaseBuilder(applicationContext, UserDB::class.java, "appUser.db").build()

        val nama = sharePreference.getUser()?.nama
        val username = sharePreference.getUser()?.username
        val tanggalLahir = sharePreference.getUser()?.tglLahir
        val noTelp = sharePreference.getUser()?.noTelp
        val email = sharePreference.getUser()?.email

        binding.etNama.setText(nama)
        binding.etUsername.setText(username)
        binding.etTanggalLahir.setText(tanggalLahir)
        binding.etNoTelp.setText(noTelp)
        binding.etEmail.setText(email)

        binding.btnUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {

            }
        }
        return binding.root
    }
}