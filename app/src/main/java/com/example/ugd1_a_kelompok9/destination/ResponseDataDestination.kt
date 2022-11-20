package com.example.ugd1_a_kelompok9.destination

import com.google.gson.annotations.SerializedName

data class ResponseDataDestination (
    @SerializedName("status") val stt:String,
    val totaldata: Int,
    val data:List<DestinationData>)