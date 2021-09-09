package com.sudoajay.picsum.main.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

class PicsumInterfaceBuilderGson {
    companion object {
        var TAG = "PicsumInterfaceBuilderTAG"
        var baseUrl = "https://picsum.photos/v2/"
        var picsumApiInterface: PicsumApiInterface? = null
        var okHttpClient: OkHttpClient? = null


        fun getApiInterface(): PicsumApiInterface? {
            if (picsumApiInterface == null) {

                Log.e(TAG, "picsumApiInterface is not null ")
                //For printing API url and body in logcat
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

                okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(50, TimeUnit.SECONDS)
                    .writeTimeout(50, TimeUnit.SECONDS)
                    .callTimeout(50, TimeUnit.SECONDS)
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient!!)
                    .build()
                picsumApiInterface = retrofit.create(PicsumApiInterface::class.java)
            }
            Log.e(TAG,  "picsumApiInterface ")

            return picsumApiInterface
        }

    }

}