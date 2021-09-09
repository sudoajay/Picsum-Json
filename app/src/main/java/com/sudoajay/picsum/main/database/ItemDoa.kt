package com.sudoajay.picsum.main.database

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sudoajay.picsum.main.model.Item


@Dao
interface ItemDoa {


    @Query("DELETE FROM ItemTable")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<Item>)


    @Query("SELECT * FROM ItemTable ")
    fun pagingSource(): PagingSource<Int, Item>


}