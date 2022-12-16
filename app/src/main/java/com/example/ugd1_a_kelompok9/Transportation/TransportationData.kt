package com.example.ugd1_a_kelompok9.Transportation

import com.google.gson.annotations.SerializedName

data class TransportationData(
    @SerializedName("id") val id:Int,
    @SerializedName("nama") val nama:String,
    @SerializedName("jenis") val jenis:String,
    @SerializedName("jam_berangkat") val jam_berangkat:String,
    @SerializedName("jam_tiba") val jam_tiba:String,
    @SerializedName("tgl_berangkat") val tgl_berangkat:String,
    @SerializedName("harga") val harga:String,
)
