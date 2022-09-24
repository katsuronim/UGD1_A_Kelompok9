package com.example.ugd1_a_kelompok9.room

import androidx.room.*


@Dao
interface TourDao {

    @Insert
    suspend fun addTour(tour: Tour)

    @Update
    suspend fun updateTour(tour: Tour)

    @Delete
    suspend fun deleteTour(tour: Tour)

    @Query("SELECT * FROM Tour")
    suspend fun getTours(): List<Tour>

    @Query("SELECT * FROM tour WHERE id =:tour_id")
    suspend fun getTour(tour_id: Int): List<Tour>
}