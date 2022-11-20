package com.example.ugd1_a_kelompok9.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ugd1_a_kelompok9.Data.RClient
import com.example.ugd1_a_kelompok9.Data.ResponseDataUser
import com.example.ugd1_a_kelompok9.Data.UserData
import com.example.ugd1_a_kelompok9.R
import com.example.ugd1_a_kelompok9.databinding.ActivityDetailUserBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    private lateinit var inputUsername: TextInputLayout
    private lateinit var inputPassword: TextInputLayout
    private lateinit var checkUsername: TextInputLayout
    private lateinit var checkPassword: TextInputLayout
    private lateinit var mainLayout: ConstraintLayout
//    private lateinit var sharePreference: SharePreference
//    private lateinit var db: UserDB
//    lateinit var mBundle: Bundle
    private lateinit var binding : ActivityDetailUserBinding
    private var b:Bundle? = null
    private val listUser = ArrayList<UserData>()
    private var vUsername: String = "null"
    private var vPassword: String = "null"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding =
            ActivityDetailUserBinding.inflate(layoutInflater)
        val extras = Bundle()
//        getBundle()
//        sharePreference = SharePreference(this)
        inputUsername = findViewById(R.id.inputLayoutUsername)
        inputPassword = findViewById(R.id.inputLayoutPassword)
        checkUsername = findViewById(R.id.checkInputUsername)
        checkPassword = findViewById(R.id.checkInputPassword)
        mainLayout = findViewById(R.id.mainLayout)
        val btnClear: Button = findViewById(R.id.btnClear)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val btnRegister: Button = findViewById(R.id.btnRegister)

        btnClear.setOnClickListener{
            inputUsername.getEditText()?.setText("")
            inputPassword.getEditText()?.setText("")

            Snackbar.make(mainLayout, "Text Cleared Success", Snackbar.LENGTH_LONG).show()
        }

        btnRegister.setOnClickListener{
            val moveRegister = Intent(this, FormAddUserActivity::class.java)
            startActivity(moveRegister)
        }

        btnLogin.setOnClickListener(View.OnClickListener {

            val username: String = inputUsername.getEditText()?.getText().toString()
            val password: String = inputPassword.getEditText()?.getText().toString()

            getDataDetail(username)

            if (username.isEmpty()) {
                inputUsername.setError("Username must be filled with text")
            }
            if (password.isEmpty()) {
                inputPassword.setError("Password must be filled with text")
            }
            if (username == vUsername && password == vPassword) {
                extras.putString("username", vUsername)
                extras.putString("password", vPassword)
                val intent = Intent(this@LoginActivity, MainPageActivity::class.java)
                intent.putExtras(extras)
                startActivity(intent)
            }
            if (username != vUsername && password != vPassword) {
                inputUsername.setError("Username salah!")
                inputPassword.setError("Password salah!")
                inputUsername.getEditText()?.setText(vUsername)
                inputPassword.getEditText()?.setText(vPassword)
            }
            if (username != vUsername) {
                inputUsername.setError("Username salah!")
            }
            if (password != vPassword) {
                inputPassword.setError("Password salah!")
            }


//        btnLogin.setOnClickListener(View.OnClickListener {
//            var checkLogin = false
//            val username: String = inputUsername.getEditText()?.getText().toString()
//            val password: String = inputPassword.getEditText()?.getText().toString()
//            db = Room.databaseBuilder(applicationContext, UserDB::class.java, "appUser.db").build()
//            var userId: Int = 0
//            var pasId: Int = 0
//            var checkUser: String
//            var checkPass: String
//
//            if (username.isEmpty()) {
//                inputUsername.setError("Username must be filled with text")
//                checkLogin = false
//            }
//            if (password.isEmpty()) {
//                inputPassword.setError("Password must be filled with text")
//                checkLogin = false
//            }
//
//            CoroutineScope(Dispatchers.IO).launch {
//                val user = db.userDao().getUser(username, password)
//
//                if (user == null) {
//                    Log.d("LoginActivity", "USER IS NULL")
//                    withContext(Dispatchers.Main) {
//                        inputUsername.setError("Username tidak sesuai !")
//                        inputPassword.setError("Password tidak sesuai !")
//                        checkLogin = false
//
//                    }
////                    return@OnClickListener
//                } else {
//                    Log.d("LoginActivity", "USER FOUND")
//                    withContext(Dispatchers.Main) {
//                        val moveHome = Intent(this@LoginActivity, MainPageActivity::class.java)
//                        startActivity(moveHome)
//                        checkLogin = true
//                        sharePreference.setUser(user)
//
//                    }
//                }
//            }
//        })
//            if (username == checkUser && password == checkPass) checkLogin = true
//            if (username != "admin" && password != "kelompok9") {
//
//
//                if (username.isNotEmpty() && password.isNotEmpty()) {
//                    inputUsername.setError("Username salah!")
//                    inputPassword.setError("Password salah!")
//                    checkLogin = false
//                }
//                return@OnClickListener
//            }
//            }
//
//            fun getBundle() {
//                val bundle: Bundle? = intent.extras
//                val name: String? = bundle?.getString("admin")
//                val pass: String? = bundle?.getString("kelompok9")
//
//                inputUsername = findViewById(R.id.inputLayoutUsername)
//                inputUsername.getEditText()?.setText(name)
//
//                inputPassword = findViewById(R.id.inputLayoutPassword)
//                inputPassword.getEditText()?.setText(pass)
//            }


        })
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
                        checkUsername.getEditText()?.setText(listUser[0].username)
                        checkUsername.getEditText()?.setText(listUser[0].password)
                        vUsername = listUser[0].username
                        vPassword = listUser[0].password
                    }
                }
            }
            override fun onFailure(call: Call<ResponseDataUser>, t: Throwable) {
            }
        })
    }
}