package com.example.ugd1_a_kelompok9.Transportation

import com.example.ugd1_a_kelompok9.apiDestination
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RClientTransportation {
    private const val BASE_URL = "http://192.168.1.53/ci4-apiserver/public/"

    val instances: apiTransportation by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(apiTransportation::class.java)
    }
}