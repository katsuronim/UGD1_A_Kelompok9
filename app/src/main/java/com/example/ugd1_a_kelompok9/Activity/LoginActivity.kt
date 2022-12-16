package com.example.ugd1_a_kelompok9.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ugd1_a_kelompok9.Data.RClient
import com.example.ugd1_a_kelompok9.Data.ResponseCreate
import com.example.ugd1_a_kelompok9.Data.ResponseDataUser
import com.example.ugd1_a_kelompok9.Data.UserData
import com.example.ugd1_a_kelompok9.R
import com.example.ugd1_a_kelompok9.databinding.ActivityDetailUserBinding
import com.example.ugd1_a_kelompok9.databinding.ActivityFormEditUserBinding
import com.example.ugd1_a_kelompok9.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.romainpiel.shimmer.Shimmer
import com.romainpiel.shimmer.ShimmerTextView
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_form_add_user.*
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class  LoginActivity : AppCompatActivity() {
    var tv: ShimmerTextView? = null
    var shimmer: Shimmer? = null
    private lateinit var inputEmail: TextInputLayout
    private lateinit var inputPassword: TextInputLayout
    private lateinit var checkUsername: TextInputLayout
    private lateinit var checkPassword: TextInputLayout
    private lateinit var mainLayout: ConstraintLayout
//    private lateinit var sharePreference: SharePreference
//    private lateinit var db: UserDB
//    lateinit var mBundle: Bundle
    private lateinit var binding : ActivityLoginBinding
    private var b:Bundle? = null
    private val listUser = ArrayList<UserData>()
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding =
            ActivityLoginBinding.inflate(layoutInflater)
        val extras = Bundle()
        auth = FirebaseAuth.getInstance()
//        getBundle()
//        sharePreference = SharePreference(this)
        inputEmail = findViewById(R.id.inputLayoutEmail)
        inputPassword = findViewById(R.id.inputLayoutPassword)
        mainLayout = findViewById(R.id.mainLayout)
        val btnClear: Button = findViewById(R.id.btnClear)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val btnRegister: Button = findViewById(R.id.btnRegister)
        val tv: ShimmerTextView = findViewById(R.id.textView)

        shimmer = Shimmer()
        shimmer!!.start(tv)

        btnClear.setOnClickListener {
            inputEmail.getEditText()?.setText("")
            inputPassword.getEditText()?.setText("")

            Snackbar.make(mainLayout, "Text Cleared Success", Snackbar.LENGTH_LONG).show()
        }

        btnRegister.setOnClickListener {
            val moveRegister = Intent(this, FormAddUserActivity::class.java)
            startActivity(moveRegister)
        }

        btnLogin.setOnClickListener(View.OnClickListener {

            val email: String = inputEmail.getEditText()?.getText().toString()
            val password: String = inputPassword.getEditText()?.getText().toString()

            //getDataDetail(username)
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){ task ->
                if (task.isSuccessful) {
                    if(auth.currentUser?.isEmailVerified == true){
                        RClient.instances.checkLogin(email, password).enqueue(object :
                            Callback<ResponseCreate> {
                            override fun onResponse(
                                call: Call<ResponseCreate>,
                                response: Response<ResponseCreate>
                            ) {
                                if (response.isSuccessful) {
                                    extras.putString("email", email)
                                    extras.putString("password", password)
                                    val intent = Intent(this@LoginActivity, MainPageActivity::class.java)
                                    intent.putExtras(extras)
                                    startActivity(intent)
                                    FancyToast.makeText(
                                        applicationContext, "${response.body()?.pesan}",
                                        FancyToast.LENGTH_LONG,
                                        FancyToast.SUCCESS, true
                                    ).show()
                                    finish()
                                } else {
                                    val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())

//                        txtUsername.setError(jsonObj.getString("message"))
                                    inputEmail.setError("Email salah!")
                                    inputPassword.setError("Password salah!")
                                    FancyToast.makeText(
                                        applicationContext, jsonObj.getString("message"),
                                        FancyToast.LENGTH_LONG,
                                        FancyToast.ERROR, true
                                    ).show()
                                    //FancyToast.makeText(applicationContext,"Maaf sudah ada datanya",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show()
                                }
                            }

                            override fun onFailure(
                                call:
                                Call<ResponseCreate>, t: Throwable
                            ) {
                            }
                        })
                    } else {
                        FancyToast.makeText(applicationContext,"Anda belum verifikasi email, mohon verifikasi terlebih dahulu!",
                            FancyToast.LENGTH_LONG,
                            FancyToast.ERROR,true).show()
                    }
                } else {
                    FancyToast.makeText(applicationContext,task.exception.toString(),
                        FancyToast.LENGTH_LONG,
                        FancyToast.ERROR,true).show()
                }
            }
        })
    }
}