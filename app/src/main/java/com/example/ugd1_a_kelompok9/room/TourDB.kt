package com.example.ugd1_a_kelompok9.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Tour::class],
    version = 1
)

abstract class TourDB: RoomDatabase() {
        abstract fun tourDao() : TourDao
        companion object {
            @Volatile private var instance : TourDB? = null
            private val LOCK = Any()
            operator fun invoke(context: Context) = instance ?:
            synchronized(LOCK){
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
            private fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                    context.applicationContext,
                    TourDB::class.java,
                    "Tour-DB.db"
                ).build()
        }
}