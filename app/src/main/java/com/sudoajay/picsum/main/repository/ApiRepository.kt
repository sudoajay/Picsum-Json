package com.sudoajay.picsum.main.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.sudoajay.picsum.R
import com.sudoajay.picsum.main.MainActivity
import com.sudoajay.picsum.main.api.PicsumInterfaceBuilderJackson
import com.sudoajay.picsum.main.api.PicsumInterfaceBuilderJson
import com.sudoajay.picsum.main.model.PersonGson
import com.sudoajay.picsum.main.model.PersonJackson
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiRepository(private var activity: MainActivity) {

    private var TAG = "ApiRepositoryTAG"
    fun getDataFromApi() {
        if (activity.viewModel.getJsonConverter == activity.getString(R.string.jacksonJson_text)) {
            val apiInterface =
                PicsumInterfaceBuilderJackson.getApiInterface()
            getJacksonAPI(apiInterface?.getPersonJackson())
        } else {
            val apiInterface =
                PicsumInterfaceBuilderJson.getApiInterface()
            getGsonApi(apiInterface?.getPersonGson())
        }


    }

    private fun getJacksonAPI(call: Call<List<PersonJackson>?>?) {
        call?.enqueue(object : Callback<List<PersonJackson>?> {

            override fun onResponse(
                call: Call<List<PersonJackson>?>,
                response: Response<List<PersonJackson>?>
            ) {
                activity.lifecycleScope.launch {
                    activity.personListAdapter.personJacksons = response.body() ?: listOf()
                    activity.binding.recyclerView.adapter = activity.personListAdapter


                }
            }

            override fun onFailure(call: Call<List<PersonJackson>?>, t: Throwable) {
                Log.e("$TAG +onFailure", t.printStackTrace().toString() + " -- $t")
                Toast.makeText(
                    activity.applicationContext,
                    activity.getString(R.string.noDataFound_text),
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun getGsonApi(call: Call<List<PersonGson>?>?) {
        call?.enqueue(object : Callback<List<PersonGson>?> {

            override fun onResponse(
                call: Call<List<PersonGson>?>,
                response: Response<List<PersonGson>?>
            ) {
                activity.lifecycleScope.launch {
                    activity.personListAdapter.personGson = response.body() ?: listOf()
                    activity.binding.recyclerView.adapter = activity.personListAdapter

                }
            }

            override fun onFailure(call: Call<List<PersonGson>?>, t: Throwable) {
                Log.e("$TAG +onFailure", t.printStackTrace().toString() + " -- $t")
                Toast.makeText(
                    activity.applicationContext,
                    activity.getString(R.string.noDataFound_text),
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}