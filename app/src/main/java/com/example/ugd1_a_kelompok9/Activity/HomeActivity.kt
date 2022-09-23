package com.example.ugd1_a_kelompok9.Activity

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.ugd1_a_kelompok9.Fragment.FragmentAkun
import com.example.ugd1_a_kelompok9.Fragment.FragmentCreator
import com.example.ugd1_a_kelompok9.Fragment.FragmentUser
import com.example.ugd1_a_kelompok9.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        changeFragment(FragmentUser())
    }

    fun changeFragment(fragment: Fragment?) {
        if(fragment != null){
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_fragment, fragment)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater =  MenuInflater(this)
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_user){
            changeFragment(FragmentAkun())
        } else if(item.itemId == R.id.menu_creator){
            changeFragment(FragmentCreator())
        } else {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this@HomeActivity)
            builder.setMessage("Are you sure want to exit?")
                .setPositiveButton("YES", object : DialogInterface.OnClickListener {
                    override fun onClick(dialogInterface: DialogInterface, i: Int){
                        finishAndRemoveTask()
                    }
                })
                .show()
        }
        return super.onOptionsItemSelected(item)
    }
}