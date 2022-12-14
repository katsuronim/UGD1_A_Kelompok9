package com.example.ugd1_a_kelompok9.Transportation

import com.example.ugd1_a_kelompok9.Data.UserData
import com.example.ugd1_a_kelompok9.Transportation.TransportationData
import com.google.gson.annotations.SerializedName

data class ResponseDataTransportation(
    @SerializedName("status") val stt:String,
    val totaldata: Int,
    val data:List<TransportationData>
)
