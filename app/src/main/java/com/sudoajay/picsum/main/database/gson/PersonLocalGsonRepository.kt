package com.sudoajay.picsum.main.database.gson

import androidx.paging.PagingSource
import com.sudoajay.picsum.main.model.local.PersonLocalGson


class PersonLocalGsonRepository(private val personLocalGsonDoa: PersonLocalGsonDoa) {


    fun pagingSource(): PagingSource<Int, PersonLocalGson> = personLocalGsonDoa.pagingSource()


    suspend fun insertAll(list: List<PersonLocalGson>) = personLocalGsonDoa.insertAll(list)

    suspend fun deleteAll() = personLocalGsonDoa.deleteAll()


}