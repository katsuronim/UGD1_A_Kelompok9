package com.example.ugd1_a_kelompok9.room

import androidx.room.*

@Dao
interface DestinationDao {

    @Insert
    suspend fun addDestination(destination: Destination)

    @Update
    suspend fun updateDestination(destination: Destination)

    @Delete
    suspend fun deleteDestination(destination: Destination)

    @Query("SELECT * FROM Destination")
    suspend fun getDestinations() : List<Destination>

    @Query("SELECT * FROM Destination WHERE destID =:destination_id")
    suspend fun getDestination(destination_id: Int) : List<Destination>
}