package com.example.ugd1_a_kelompok9

import com.example.ugd1_a_kelompok9.Data.ResponseCreate
import com.example.ugd1_a_kelompok9.destination.ResponseDataDestination
import retrofit2.Call
import retrofit2.http.*

interface apiDestination {
    @GET("destinations/{cari}")
    fun getData(@Path("cari") cari: String? = null): Call<ResponseDataDestination>

    @FormUrlEncoded
    @POST("destinations")
    fun createData(
        @Field("nama") nama: String?,
        @Field("tanggal_berangkat") tanggal_berangkat: String?,
        @Field("tanggal_pulang") tanggal_pulang: String?,
        @Field("harga") harga: String?,
        @Field("deskripsi") deskripsi: String?,
    ): Call<ResponseCreate>

    @DELETE("destinations/{id}")
    fun deleteData(
        @Path("id") id: String?
    ): Call<ResponseCreate>

    @FormUrlEncoded
    @PUT("destinations/{id}")
    fun updateData(
        @Path("id") id: String?,
        @Field("nama") nama: String?,
        @Field("tanggal_berangkat") tanggal_berangkat: String?,
        @Field("tanggal_pulang") tanggal_pulang: String?,
        @Field("harga") harga: String?,
        @Field("deskripsi") deskripsi: String?,
    ): Call<ResponseCreate>
}
