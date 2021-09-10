package com.sudoajay.picsum.main.database.gson

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sudoajay.picsum.main.model.local.PersonLocalGson

@Database(entities = [PersonLocalGson::class], version = 1 , exportSchema = false)
abstract class PersonLocalGsonDatabase : RoomDatabase() {

    abstract fun itemDoa(): PersonLocalGsonDoa

    companion object {
        @Volatile
        private var INSTANCE: PersonLocalGsonDatabase? = null

        fun getDatabase(
            context: Context
        ): PersonLocalGsonDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonLocalGsonDatabase::class.java,
                    "PersonGsonTable_database"
                )

                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}