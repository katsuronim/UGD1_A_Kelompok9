package com.example.ugd1_a_kelompok9.Activity

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ugd1_a_kelompok9.Fragment.FragmentAkun
import com.example.ugd1_a_kelompok9.Fragment.FragmentCreator
import com.example.ugd1_a_kelompok9.Fragment.FragmentMain
import com.example.ugd1_a_kelompok9.R


class MainPageActivity : AppCompatActivity() {
    private var username: String = ""
    private var password: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val intent = intent

        val extras = intent.extras

        username = extras!!.getString("username").toString()
        password = extras!!.getString("password").toString()
        val firstFragment= FragmentMain()
        val secondFragment= FragmentAkun()
        val thirdFragment= FragmentCreator()

        val bottomNavigationView = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(
            R.id.bottomNavigationView
        )

        setCurrentFragment(firstFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home ->setCurrentFragment(firstFragment)
                R.id.user ->setCurrentFragment(secondFragment)
                R.id.admin ->setCurrentFragment(thirdFragment)
                R.id.logout ->{
                    val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainPageActivity)
                    builder.setMessage("Are you sure want to exit?")
                        .setPositiveButton("YES", object : DialogInterface.OnClickListener {
                            override fun onClick(dialogInterface: DialogInterface, i: Int){
                                finishAndRemoveTask()
                            }
                        })
                        .show()
                }
            }
            true
        }
    }

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }

    public fun getUsername(): String {
        return username
    }

    public fun getPassword(): String {
        return password
    }
}