package com.example.ugd1_a_kelompok9

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RClientDestination {
    private const val BASE_URL = "http://192.168.100.213/ci4-apiserver/public/"

    val instances: apiDestination by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(apiDestination::class.java)
    }
}