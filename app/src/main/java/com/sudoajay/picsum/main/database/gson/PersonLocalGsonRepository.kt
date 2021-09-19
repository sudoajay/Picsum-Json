package com.sudoajay.picsum.main.database.gson

import androidx.paging.PagingSource
import com.sudoajay.picsum.main.model.local.PersonLocalGson


class PersonLocalGsonRepository(private val personLocalGsonDoa: PersonLocalGsonDoa) {


    fun pagingSource(search: String?): PagingSource<Int, PersonLocalGson> =
        personLocalGsonDoa.pagingSource(search)


    suspend fun insertAll(list: List<PersonLocalGson>) = personLocalGsonDoa.insertAll(list)


}