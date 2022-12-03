package com.example.ugd1_a_kelompok9.Data

import retrofit2.Call
import retrofit2.http.*

interface api {
    @GET("users/{cari}")
    fun getData(@Path("cari") cari: String):
            Call<ResponseDataUser>
    @FormUrlEncoded
    @POST("users")
    fun createData(
//        @Field("id") id: String?,
        @Field("nama") nama:String?,
        @Field("username") username:String?,
        @Field("password") password:String?,
        @Field("no_telp") notelp:String?,
        @Field("tgl_lahir") tgllahir:String?,
        @Field("email") email:String?,
    ):Call<ResponseCreate>
    @DELETE("users/{id}")
    fun deleteData(@Path("id") id:
                   String
    ):Call<ResponseCreate>
    @FormUrlEncoded
    @PUT("users/{id}")
    fun updateData(
        @Path("id") id: Int,
        @Field("nama") nama:String?,
        @Field("username") username:String?,
        @Field("password") password:String?,
        @Field("no_telp") notelp:String?,
        @Field("tgl_lahir") tgllahir:String?,
        @Field("email") email:String?,
    ):Call<ResponseCreate>
    @FormUrlEncoded
    @POST("users/checkLogin")
    fun checkLogin(
        @Field("username") username:String?,
        @Field("password") password:String?
    ):Call<ResponseCreate>
}