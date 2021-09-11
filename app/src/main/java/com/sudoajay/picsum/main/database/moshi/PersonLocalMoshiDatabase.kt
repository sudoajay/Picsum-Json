package com.sudoajay.picsum.main.database.moshi

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sudoajay.picsum.main.model.local.PersonLocalMoshi

@Database(entities = [PersonLocalMoshi::class], version = 1, exportSchema = false)
abstract class PersonLocalMoshiDatabase : RoomDatabase() {

    abstract fun itemDoa(): PersonLocalMoshiDoa

    companion object {
        @Volatile
        private var INSTANCE: PersonLocalMoshiDatabase? = null

        fun getDatabase(
            context: Context
        ): PersonLocalMoshiDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonLocalMoshiDatabase::class.java,
                    "PersonMoshiTable_database"
                )

                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}