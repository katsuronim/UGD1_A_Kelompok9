package com.example.ugd1_a_kelompok9.Data

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("id") val id:Int,
    @SerializedName("nama") val nama:String,
    @SerializedName("username") val username:String,
    @SerializedName("password") val password:String,
    @SerializedName("no_telp") val notelp:String,
    @SerializedName("tgl_lahir") val tgllhr:String,
    @SerializedName("email") val email:String,
)
