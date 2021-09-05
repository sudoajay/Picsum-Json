package com.sudoajay.picsum.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.sudoajay.picsum.BaseActivity
import com.sudoajay.picsum.R
import com.sudoajay.picsum.databinding.ActivityMainBinding
import com.sudoajay.picsum.main.api.PicsumInterfaceBuilder
import com.sudoajay.picsum.main.model.Person
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModel:MainViewModel
    private lateinit var binding :ActivityMainBinding
    private var isDarkMode:Boolean = false
    private var TAG = "MainActivityTAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isDarkMode = isSystemDefaultOn()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)



//        val apiInterface = PicsumInterfaceBuilder.getApiInterface()
//        val call = apiInterface?.getPerson()
//
//        call?.enqueue(object : Callback<List<Person>> {
//
//            override fun onResponse(call: Call<List<Person>>, response: Response<List<Person>>) {
////                Log.e("$TAG+Response", Gson().toJson(response))
//                Log.e("$TAG+Response", response.message())
//                Log.e("$TAG+Response"," Start ")
//                response.body()?.forEach {
//                    Log.e("$TAG + Response", it.toString())
//                }
//                Log.e("$TAG+Response","End")
//
//            }
//            override fun onFailure(call: Call<List<Person>>, t: Throwable)
//            {
//                Log.e("$TAG +onFailure",t.printStackTrace().toString() + " -- ${t.toString()}")
//            }
//        })

    }
}