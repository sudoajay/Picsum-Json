package com.sudoajay.picsum.main.database.moshi

import androidx.paging.PagingSource
import com.sudoajay.picsum.main.model.local.PersonLocalJackson
import com.sudoajay.picsum.main.model.local.PersonLocalMoshi


class PersonLocalMoshiRepository(private val personLocalMoshiDoa: PersonLocalMoshiDoa) {


    fun pagingSource(search: String?): PagingSource<Int, PersonLocalMoshi> = personLocalMoshiDoa.pagingSource(search)


    suspend fun insertAll(list: List<PersonLocalMoshi>) = personLocalMoshiDoa.insertAll(list)

    suspend fun deleteAll() = personLocalMoshiDoa.deleteAll()


}