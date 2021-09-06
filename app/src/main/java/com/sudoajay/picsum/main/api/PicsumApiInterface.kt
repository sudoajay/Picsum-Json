package com.sudoajay.picsum.main.api

import androidx.paging.PagedList
import androidx.paging.PagingData
import com.sudoajay.picsum.main.model.Person
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET




interface PicsumApiInterface {

    /**
     * The return type is important here
     * The class structure that you've defined in Call<T>
     * should exactly match with your json response
     * If you are not using another api, and using the same as mine
     * then no need to worry, but if you have your own API, make sure
     * you change the return type appropriately
    </T> */
    @GET("list")
    fun getPerson(): Call<PagingData<Person>>

}