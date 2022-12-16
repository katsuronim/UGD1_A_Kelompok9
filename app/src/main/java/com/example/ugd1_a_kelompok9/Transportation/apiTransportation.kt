package com.example.ugd1_a_kelompok9.Transportation

import com.example.ugd1_a_kelompok9.Data.ResponseCreate
import com.example.ugd1_a_kelompok9.Data.ResponseDataUser
import retrofit2.Call
import retrofit2.http.*

interface apiTransportation {
    @GET("transportation/{cari}")
    fun getData(@Path("cari") cari: String):
            Call<ResponseDataTransportation>
    @GET("transportation/{cari}")
    fun getDataById(@Path("cari") cari: Int):
            Call<ResponseDataTransportation>
    @FormUrlEncoded
    @POST("transportation")
    fun createData(
//        @Field("id") id: String?,
        @Field("nama") nama:String?,
        @Field("jenis") jenis:String?,
        @Field("jam_berangkat") jam_berangkat:String?,
        @Field("jam_tiba") jam_tiba:String?,
        @Field("tgl_berangkat") tgl_berangkat:String?,
        @Field("harga") harga:String?,
    ): Call<ResponseCreateTransportation>
    @DELETE("transportation/{id}")
    fun deleteData(@Path("id") id:
                   String
    ): Call<ResponseCreateTransportation>
    @FormUrlEncoded
    @PUT("transportation/{id}")
    fun updateData(
        @Path("id") id: Int?,
        @Field("nama") nama:String?,
        @Field("jenis") jenis:String?,
        @Field("jam_berangkat") jam_berangkat:String?,
        @Field("jam_tiba") jam_tiba:String?,
        @Field("tgl_berangkat") tgl_berangkat:String?,
        @Field("harga") harga:String?,
    ): Call<ResponseCreateTransportation>
}