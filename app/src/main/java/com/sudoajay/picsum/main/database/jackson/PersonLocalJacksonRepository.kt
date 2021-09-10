package com.sudoajay.picsum.main.database.jackson

import androidx.paging.PagingSource
import com.sudoajay.picsum.main.model.local.PersonLocalJackson


class PersonLocalJacksonRepository(private val personLocalJacksonDoa: PersonLocalJacksonDoa) {


    fun pagingSource(): PagingSource<Int, PersonLocalJackson> = personLocalJacksonDoa.pagingSource()


    suspend fun insertAll(list: List<PersonLocalJackson>) = personLocalJacksonDoa.insertAll(list)

    suspend fun deleteAll() = personLocalJacksonDoa.deleteAll()


}