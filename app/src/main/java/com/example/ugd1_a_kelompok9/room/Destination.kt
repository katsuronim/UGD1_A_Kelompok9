package com.example.ugd1_a_kelompok9.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Destination (
        @PrimaryKey(autoGenerate = true)
        val destID : Int,
        val placeName : String,
        val tglBerangkat : String,
        val tglPulang : String
//        val cost : Int
    )
