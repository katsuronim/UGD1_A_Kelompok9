package com.example.ugd1_a_kelompok9

import android.content.Context
import android.content.SharedPreferences
import com.example.ugd1_a_kelompok9.room.User
import com.google.gson.Gson

class SharePreference(var context: Context?) {
    val PRIVATE_MODE = 0

    private val PREF_NAME = "SharedPreferences"

    var pref: SharedPreferences? = context?.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    var editor: SharedPreferences.Editor? = pref?.edit()


    fun setUser(user: User){
        var json = Gson().toJson(user)
        editor?.putString("user", json)
        editor?.commit()
    }

    fun getUser(): User? {
        var json = Gson().fromJson(pref?.getString("user",""), User::class.java)
        return json
    }
}