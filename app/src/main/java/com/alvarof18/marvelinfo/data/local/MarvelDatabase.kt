package com.alvarof18.marvelinfo.data.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ComicEntity::class], version = 1)
abstract class MarvelDatabase : RoomDatabase() {

    //DAO
    abstract fun MarvelDao(): MarvelDao

    companion object {
        @Volatile
        private var Instance: MarvelDatabase? = null

        fun getDataBase(context: Context): MarvelDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            Log.i("InstancesDB", "$Instance")
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MarvelDatabase::class.java, "marveldb").build()
                    .also { it }
            }
        }
    }
}

