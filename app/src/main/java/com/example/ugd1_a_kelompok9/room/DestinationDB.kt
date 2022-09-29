package com.example.ugd1_a_kelompok9.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Destination::class],
    version = 1
)
abstract class DestinationDB: RoomDatabase() {
    abstract fun destinationDao() : DestinationDao
    companion object {
        @Volatile private var instance : DestinationDB? = null
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
                DestinationDB::class.java,
                "destination_database.db"
            ).build()
    }
}