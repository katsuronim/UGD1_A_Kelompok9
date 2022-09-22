package com.example.ugd1_a_kelompok9.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val username: String,
        val email: String
    )