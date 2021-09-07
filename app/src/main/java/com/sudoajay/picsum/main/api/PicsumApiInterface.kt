package com.sudoajay.picsum.main.api

import com.sudoajay.picsum.main.model.PersonGson
import com.sudoajay.picsum.main.model.PersonJackson
import retrofit2.Call
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
    fun getPersonJackson(): Call<List<PersonJackson>?>?

    @GET("list")
    fun getPersonGson(): Call<List<PersonGson>?>?

}