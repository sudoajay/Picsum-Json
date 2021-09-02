package com.sudoajay.picsum.main.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sudoajay.picsum.main.model.Item

@Database(entities = [Item::class], version = 1 , exportSchema = false)
abstract class ItemRoomDatabase : RoomDatabase() {

    abstract fun itemDoa():ItemDoa

    companion object {
        @Volatile
        private var INSTANCE: ItemRoomDatabase? = null

        fun getDatabase(
            context: Context
        ): ItemRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemRoomDatabase::class.java,
                    "item_database"
                )

                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}