package com.sudoajay.picsum.main.repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sudoajay.picsum.R
import com.sudoajay.picsum.helper.Toaster
import com.sudoajay.picsum.main.MainActivity
import com.sudoajay.picsum.main.api.PicsumInterfaceBuilderGson
import com.sudoajay.picsum.main.api.PicsumInterfaceBuilderJackson
import com.sudoajay.picsum.main.api.PicsumInterfaceBuilderMoshi
import com.sudoajay.picsum.main.background.pagingSource.PagingSourceNetworkGson
import com.sudoajay.picsum.main.background.pagingSource.PagingSourceNetworkJackson
import com.sudoajay.picsum.main.background.pagingSource.PagingSourceNetworkMoshi
import com.sudoajay.picsum.main.background.remoteMediator.RemoteMediatorGson
import com.sudoajay.picsum.main.background.remoteMediator.RemoteMediatorJackson
import com.sudoajay.picsum.main.background.remoteMediator.RemoteMediatorMoshi
import com.sudoajay.picsum.main.database.gson.PersonLocalGsonDatabase
import com.sudoajay.picsum.main.database.gson.PersonLocalGsonRepository
import com.sudoajay.picsum.main.database.jackson.PersonLocalJacksonDatabase
import com.sudoajay.picsum.main.database.jackson.PersonLocalJacksonRepository
import com.sudoajay.picsum.main.database.moshi.PersonLocalMoshiDatabase
import com.sudoajay.picsum.main.database.moshi.PersonLocalMoshiRepository
import com.sudoajay.picsum.main.model.local.PersonLocalGson
import com.sudoajay.picsum.main.model.local.PersonLocalJackson
import com.sudoajay.picsum.main.model.local.PersonLocalMoshi
import com.sudoajay.picsum.main.model.remote.PersonGson
import com.sudoajay.picsum.main.model.remote.PersonJackson
import com.sudoajay.picsum.main.model.remote.PersonMoshi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("NotifyDataSetChanged")
class ApiRepository(private var activity: MainActivity) {

    private var TAG = "ApiRepositoryTAG"
    fun getDataFromApi() {
        Log.e(TAG, "getDataFromApi: ${activity.viewModel.searchValue}")
        when (activity.viewModel.getJsonConverter) {
            activity.getString(R.string.jacksonJson_text) -> {
                val apiInterface =
                    PicsumInterfaceBuilderJackson.getApiInterface()
                getJacksonAPI(apiInterface?.getPersonJackson())
            }
            activity.getString(R.string.gsonJson_text) -> {
                val apiInterface =
                    PicsumInterfaceBuilderGson.getApiInterface()
                getGsonApi(apiInterface?.getPersonGson())
            }
            else -> {
                val apiInterface =
                    PicsumInterfaceBuilderMoshi.getApiInterface()
                getMoshiApi(apiInterface?.getPersonMoshi())
            }
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
                    activity.personListAdapter.personMoshi = listOf()
                    activity.viewModel.hideProgress.postValue(true)
                    activity.personListAdapter.personJackson =
                        response.body()
                            ?.filter { if (activity.viewModel.searchValue != "") it.name.lowercase().contains(activity.viewModel.searchValue)  else true }
                            ?: listOf()
                    activity.binding.recyclerView.adapter?.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<List<PersonJackson>?>, t: Throwable) {
                Log.e("$TAG +onFailure", t.printStackTrace().toString() + " -- $t")
                Toaster.showToast(  activity.applicationContext,
                    activity.getString(R.string.somethingWentWrong_text))
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
                    activity.personListAdapter.personJackson = listOf()
                    activity.personListAdapter.personMoshi = listOf()
                    activity.viewModel.hideProgress.postValue(true)

                    activity.personListAdapter.personGson =
                        response.body()
                            ?.filter { if (activity.viewModel.searchValue != "") it.name.lowercase().contains(activity.viewModel.searchValue)  else true }
                            ?: listOf()
                    activity.binding.recyclerView.adapter?.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<List<PersonGson>?>, t: Throwable) {
                Log.e("$TAG +onFailure", t.printStackTrace().toString() + " -- $t")
                Toaster.showToast(  activity.applicationContext,
                    activity.getString(R.string.somethingWentWrong_text))

            }
        })
    }

    private fun getMoshiApi(call: Call<List<PersonMoshi>?>?) {
        call?.enqueue(object : Callback<List<PersonMoshi>?> {

            override fun onResponse(
                call: Call<List<PersonMoshi>?>,
                response: Response<List<PersonMoshi>?>
            ) {
                activity.lifecycleScope.launch {
                    activity.personListAdapter.personJackson = listOf()
                    activity.personListAdapter.personGson = listOf()
                    activity.viewModel.hideProgress.postValue(true)

                    activity.personListAdapter.personMoshi = response.body()
                        ?.filter { if (activity.viewModel.searchValue != "") it.name.lowercase().contains(activity.viewModel.searchValue)  else true }
                        ?: listOf()
                    activity.binding.recyclerView.adapter?.notifyDataSetChanged()


                }
            }

            override fun onFailure(call: Call<List<PersonMoshi>?>, t: Throwable) {
                Log.e("$TAG +onFailure", t.printStackTrace().toString() + " -- $t")
                Toaster.showToast(  activity.applicationContext,
                    activity.getString(R.string.somethingWentWrong_text))
            }
        })
    }
    fun getPagingJacksonSourceWithNetwork(): Flow<PagingData<PersonJackson>> {

        val apiInterface =
            PicsumInterfaceBuilderJackson.getApiInterface()

        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                PagingSourceNetworkJackson(
                    apiInterface!!,
                    activity.applicationContext
                )
            }

        ).flow

    }

    fun getPagingGsonSourceWithNetwork(): Flow<PagingData<PersonGson>> {

        val apiInterface =
            PicsumInterfaceBuilderGson.getApiInterface()

        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                PagingSourceNetworkGson(
                    apiInterface!!,
                    activity.applicationContext
                )
            }
        ).flow
    }

    fun getPagingMoshiSourceWithNetwork(): Flow<PagingData<PersonMoshi>> {

        val apiInterface =
            PicsumInterfaceBuilderMoshi.getApiInterface()

        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                PagingSourceNetworkMoshi(
                    apiInterface!!,
                    activity.applicationContext
                )
            }
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
            remoteMediator = RemoteMediatorJackson(
                database,
                itemRepository,
                apiInterface!!
            )
        ) {
            itemRepository.pagingSource("%${activity.viewModel.searchValue}%")
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
            remoteMediator = RemoteMediatorGson(
                database,
                itemRepository,
                apiInterface!!
            )
        ) {
            itemRepository.pagingSource("%${activity.viewModel.searchValue}%")
        }.flow
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getRemoteMediatorSourceWithNetworkMoshi(): Flow<PagingData<PersonLocalMoshi>> {
        val database = PersonLocalMoshiDatabase.getDatabase(activity.applicationContext)
        val itemRepository = PersonLocalMoshiRepository(database.itemDoa())

        val apiInterface =
            PicsumInterfaceBuilderMoshi.getApiInterface()
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = RemoteMediatorMoshi(
                database,
                itemRepository,
                apiInterface!!
            )
        ) {
            itemRepository.pagingSource("%${activity.viewModel.searchValue}%")
        }.flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }

}