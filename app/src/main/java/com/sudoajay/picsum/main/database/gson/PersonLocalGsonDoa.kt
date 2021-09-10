package com.sudoajay.picsum.main.database.gson

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sudoajay.picsum.main.model.local.PersonLocalGson


@Dao
interface PersonLocalGsonDoa {
    @Query("DELETE FROM PersonGsonTable")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<PersonLocalGson>)


    @Query("SELECT * FROM PersonGsonTable")
    fun pagingSource(): PagingSource<Int, PersonLocalGson>


}