package com.sudoajay.picsum.main.database.jackson

import androidx.paging.PagingSource
import com.sudoajay.picsum.main.model.local.PersonLocalJackson


class PersonLocalJacksonRepository(private val personLocalJacksonDoa: PersonLocalJacksonDoa) {


    fun pagingSource(search: String?): PagingSource<Int, PersonLocalJackson> = personLocalJacksonDoa.pagingSource(search)


    suspend fun insertAll(list: List<PersonLocalJackson>) = personLocalJacksonDoa.insertAll(list)



}