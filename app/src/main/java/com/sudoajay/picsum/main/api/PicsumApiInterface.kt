package com.sudoajay.picsum.main.api

import com.sudoajay.picsum.main.model.local.PersonLocalGson
import com.sudoajay.picsum.main.model.local.PersonLocalJackson
import com.sudoajay.picsum.main.model.remote.PersonGson
import com.sudoajay.picsum.main.model.remote.PersonJackson
import com.sudoajay.picsum.main.model.remote.PersonMoshi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


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
    fun getPersonJackson(): Call<List<PersonJackson>?>?

    @GET("list")
    fun getPersonGson(): Call<List<PersonGson>?>?

    @GET("list")
    fun getPersonMoshi(): Call<List<PersonMoshi>?>?



    @GET("list")
    suspend fun getPersonJacksonPaging(@Query("page") page: Int, @Query("limit") size: Int): List<PersonJackson>


    @GET("list")
    suspend fun getPersonGsonPaging(@Query("page") page: Int, @Query("limit") size: Int): List<PersonGson>

    @GET("list")
    suspend fun getLocalPersonJacksonPaging(@Query("page") page: Int, @Query("limit") size: Long?): List<PersonLocalJackson>


    @GET("list")
    suspend fun getLocalPersonGsonPaging(@Query("page") page: Int, @Query("limit") size: Long?): List<PersonLocalGson>


    companion object{
        const val baseUrl = "https://picsum.photos/v2/"

    }



}