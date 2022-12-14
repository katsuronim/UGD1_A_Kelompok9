package com.example.ugd1_a_kelompok9.Transportation

import com.google.gson.annotations.SerializedName

class ResponseCreateTransportation (
    @SerializedName("status") val stt:Int,
    @SerializedName("error") val e:Boolean,
    @SerializedName("message") val pesan:String,
    )