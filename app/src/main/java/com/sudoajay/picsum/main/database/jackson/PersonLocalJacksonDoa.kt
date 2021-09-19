package com.sudoajay.picsum.main.database.jackson

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sudoajay.picsum.main.model.local.PersonLocalJackson


@Dao
interface PersonLocalJacksonDoa {
    @Query("DELETE FROM PersonJacksonTable")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<PersonLocalJackson>)


    @Query("SELECT * FROM PersonJacksonTable WHERE name LIKE :search ")
    fun pagingSource(search: String?): PagingSource<Int, PersonLocalJackson>


}