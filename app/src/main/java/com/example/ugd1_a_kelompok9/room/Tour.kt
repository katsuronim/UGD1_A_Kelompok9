package com.example.ugd1_a_kelompok9.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tour (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val judul: String,
    val deskripsi: String
)