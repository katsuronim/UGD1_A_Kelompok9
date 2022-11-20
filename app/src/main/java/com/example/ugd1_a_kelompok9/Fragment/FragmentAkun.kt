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
    private var username: String = ""
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
        username = activity!!.getUsername()
        password = activity!!.getPassword()

        val rootView: View = inflater.inflate(R.layout.fragment_akun, container, false)
//        val username = b?.getString("username")
        binding.etUsername.setText(username)
        username?.let { getDataDetail(it) }
//        sharePreference = SharePreference(requireContext())
//        userDb = UserDB.getDatabase(requireContext())
        //userDb = Room.databaseBuilder(applicationContext, UserDB::class.java, "appUser.db").build()

//        var id = sharePreference.getUser()?.userID.toString()
//        val nama = sharePreference.getUser()?.name
//        val username = sharePreference.getUser()?.username
//        var password = sharePreference.getUser()?.password
//        val tanggalLahir = sharePreference.getUser()?.tanggalLahir
//        val noTelp = sharePreference.getUser()?.noTelp
//        val email = sharePreference.getUser()?.email

        if (username != null) {
            getDataDetail(username)
        }
        binding.etUsername.setText(username)

        binding.btnUpdate.setOnClickListener {
            val intent = Intent(requireActivity(), FormEditUserActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("password", password)
            startActivity(intent)
//            CoroutineScope(Dispatchers.IO).launch {
//                val userId = id
//                val nama = binding.LayoutNama.getEditText()?.getText().toString()
//                val username = binding.LayoutUsername.getEditText()?.getText().toString()
//                val pass = password
//                val tanggalLahir = binding.LayoutTanggalLahir.getEditText()?.getText().toString()
//                val telp = binding.LayoutTelp.getEditText()?.getText().toString()
//                val email = binding.LayoutEmail.getEditText()?.getText().toString()
//
//                userDb.userDao().updateUser(userId,nama,username,pass,email,tanggalLahir,telp)
//                val user =userDb.userDao().getUserID(userId)
//                sharePreference.setUser(user)
//            }
//
//            Toast.makeText(context, "Berhasil Update", Toast.LENGTH_LONG).show()

        }
        return binding.root
    }

    fun getDataDetail(user:String){
        val username = b?.getString("username")
        username?.let { getDataDetail(it) }

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