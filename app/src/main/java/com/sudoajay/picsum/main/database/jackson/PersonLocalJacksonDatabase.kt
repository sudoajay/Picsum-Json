package com.sudoajay.picsum.main.database.jackson

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sudoajay.picsum.main.model.local.PersonLocalJackson

@Database(entities = [PersonLocalJackson::class], version = 1, exportSchema = false)
abstract class PersonLocalJacksonDatabase : RoomDatabase() {

    abstract fun itemDoa(): PersonLocalJacksonDoa

    companion object {
        @Volatile
        private var INSTANCE: PersonLocalJacksonDatabase? = null

        fun getDatabase(
            context: Context
        ): PersonLocalJacksonDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonLocalJacksonDatabase::class.java,
                    "PersonJacksonTable_database"
                )

                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}