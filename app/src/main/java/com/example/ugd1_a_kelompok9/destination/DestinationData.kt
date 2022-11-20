package com.example.ugd1_a_kelompok9.destination

import com.google.gson.annotations.SerializedName

data class DestinationData (
    @SerializedName("id") val id:String,
    @SerializedName("nama") val nama:String,
    @SerializedName("tanggal_berangkat") val tanggal_berangkat :String,
    @SerializedName("tanggal_pulang") val tanggal_pulang :String,
    @SerializedName("harga") val harga : String,
    @SerializedName("deskripsi") val deskripsi : String,
)