package com.sudoajay.picsum.main.repository

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.paging.*
import com.sudoajay.picsum.R
import com.sudoajay.picsum.main.MainActivity
import com.sudoajay.picsum.main.api.PicsumInterfaceBuilderJackson
import com.sudoajay.picsum.main.api.PicsumInterfaceBuilderGson
import com.sudoajay.picsum.main.background.pagingSource.PagingSourceNetworkGson
import com.sudoajay.picsum.main.background.pagingSource.PagingSourceNetworkJackson
import com.sudoajay.picsum.main.background.remoteMediator.RemoteMediatorGson
import com.sudoajay.picsum.main.background.remoteMediator.RemoteMediatorJackson
import com.sudoajay.picsum.main.database.gson.PersonLocalGsonRepository
import com.sudoajay.picsum.main.database.gson.PersonLocalGsonDatabase
import com.sudoajay.picsum.main.database.jackson.PersonLocalJacksonDatabase
import com.sudoajay.picsum.main.database.jackson.PersonLocalJacksonRepository
import com.sudoajay.picsum.main.model.local.PersonLocalGson
import com.sudoajay.picsum.main.model.local.PersonLocalJackson
import com.sudoajay.picsum.main.model.remote.PersonGson
import com.sudoajay.picsum.main.model.remote.PersonJackson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
@SuppressLint("NotifyDataSetChanged")
class ApiRepository(private var activity: MainActivity) {

    private var TAG = "ApiRepositoryTAG"
    fun getDataFromApi() {
        Log.e(TAG, "getDataFromApi: ${activity.viewModel.getJsonConverter}" )
        if (activity.viewModel.getJsonConverter == activity.getString(R.string.jacksonJson_text) ) {
            val apiInterface =
                PicsumInterfaceBuilderJackson.getApiInterface()
            getJacksonAPI(apiInterface?.getPersonJackson())
        } else {
            val apiInterface =
                PicsumInterfaceBuilderGson.getApiInterface()
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
                    activity.personListAdapter.personGson = listOf()
                    activity.personListAdapter.personJacksons = response.body() ?: listOf()
                    activity.binding.recyclerView.adapter?.notifyDataSetChanged()
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
                    activity.personListAdapter.personJacksons = listOf()
                    activity.personListAdapter.personGson = response.body() ?: listOf()
                    activity.binding.recyclerView.adapter?.notifyDataSetChanged()

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
    fun getPagingJacksonSourceWithNetwork(): Flow<PagingData<PersonJackson>> {

        val apiInterface =
            PicsumInterfaceBuilderJackson.getApiInterface()
        Log.e(TAG, "getPagingSourceWithNetwork: I am here at ", )

        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { PagingSourceNetworkJackson(apiInterface!!) }
        ).flow
    }

    fun getPagingGsonSourceWithNetwork(): Flow<PagingData<PersonGson>> {

        val apiInterface =
            PicsumInterfaceBuilderGson.getApiInterface()
        Log.e(TAG, "getPagingSourceWithNetwork: I am here at ", )
      
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { PagingSourceNetworkGson(apiInterface!!) }
        ).flow
    }
    @OptIn(ExperimentalPagingApi::class)

    fun getRemoteMediatorSourceWithNetworkJackson(): Flow<PagingData<PersonLocalJackson>> {
        val database = PersonLocalJacksonDatabase.getDatabase(activity.applicationContext)
        val itemRepository = PersonLocalJacksonRepository(database.itemDoa())

        val apiInterface =
            PicsumInterfaceBuilderJackson.getApiInterface()
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = RemoteMediatorJackson(database, itemRepository, apiInterface!!)
        ) {
            itemRepository.pagingSource()
        }.flow
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getRemoteMediatorSourceWithNetworkGson(): Flow<PagingData<PersonLocalGson>> {
        val database = PersonLocalGsonDatabase.getDatabase(activity.applicationContext)
        val itemRepository = PersonLocalGsonRepository(database.itemDoa())

        val apiInterface =
            PicsumInterfaceBuilderGson.getApiInterface()
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = RemoteMediatorGson(database, itemRepository, apiInterface!!)
        ) {
            itemRepository.pagingSource()
        }.flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }

}