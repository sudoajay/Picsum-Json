package com.sudoajay.picsum.main.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sudoajay.picsum.main.model.Item


@Dao
interface ItemDoa {

    @Query("Select * FROM ItemTable ")
    fun getItemList(): LiveData<List<Item>>

    @Query("DELETE FROM ItemTable")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

}