package com.example.ugd1_a_kelompok9.room

import androidx.room.*

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user: User)

    @Query ("UPDATE User SET name=:nama, username=:username, password=:password, tanggalLahir=:tglLahir, noTelp=:noTelp, email=:email WHERE userID=:id ")
    suspend fun updateUser(id: Int?,nama: String, username: String, password: String?, tglLahir: String, noTelp: String, email: String)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM User")
    suspend fun getUsers() : List<User>


    @Query("SELECT * FROM user WHERE username=:user AND password=:pass")
    suspend fun getUser(user: String , pass: String) :User

    @Query ("SELECT * FROM user ")
    fun readAllData() : List<User>

    @Query("SELECT * FROM user WHERE userID=:id")
    suspend fun getUserID(id: Int?) : User

//    @Query("SELECT * FROM User WHERE id =:user_id")
//    suspend fun getUser(user_id: Int) : List<User>

//    data class Username(
//        @ColumnInfo(name = "username") val username: String?
//    )
//
//    @Query("SELECT username from User")
//        suspend fun getUsername(): List<Username>
//
//    data class Password(
//        @ColumnInfo(name = "password") val password: String?
//    )
//
//    @Query("SELECT password from User")
//    suspend fun getPassword(): List<Password>

//    @Query("SELECT * FROM User WHERE userID =:user_id")
//    suspend fun getUser(user_id: Int) : List<User>
}