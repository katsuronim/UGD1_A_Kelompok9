package com.example.ugd1_a_kelompok9.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val nama: String,
        val username: String,
        val password: String,
        val email: String,
        val noTelp: String,
        val tglLahir: String
    )