package com.sudoajay.picsum.main.background.remoteMediator

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.sudoajay.picsum.main.api.PicsumApiInterface
import com.sudoajay.picsum.main.database.gson.PersonLocalGsonDatabase
import com.sudoajay.picsum.main.database.gson.PersonLocalGsonRepository
import com.sudoajay.picsum.main.model.local.PersonLocalGson
import java.io.IOException
import java.net.HttpRetryException

//
@OptIn(ExperimentalPagingApi::class)
class RemoteMediatorGson(
    private val database: PersonLocalGsonDatabase,
    private val personLocalGsonRepository: PersonLocalGsonRepository,
    private val picsumApiInterface: PicsumApiInterface
) : RemoteMediator<Int, PersonLocalGson>() {

    private var TAG = "RemoteMediatorGsonTAG"
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PersonLocalGson>
    ): MediatorResult {
        return try {
            // The network load method takes an optional after=<user.id>
            // parameter. For every page after the first, pass the last user
            // ID to let it continue from where it left off. For REFRESH,
            // pass null to load the first page.
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                // In this example, you never need to prepend, since REFRESH
                // will always load the first page in the list. Immediately
                // return, reporting end of pagination.
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    Log.e(TAG, "load:  last item" )
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    if (lastItem.id == 1025L)
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    else lastItem.id
                }
            }


            // Suspending network load via Retrofit. This doesn't need to be
            // wrapped in a withContext(Dispatcher.IO) { ... } block since
            // Retrofit's Coroutine CallAdapter dispatches on a worker
            // thread.
            val response = picsumApiInterface.getLocalPersonGsonPaging(1, 30)
            Log.e(TAG , "resposnse - ")
            database.withTransaction {

                // Insert new users into database, which invalidates the
                // current PagingData, allowing Paging to present the updates
                // in the DB.
                personLocalGsonRepository.insertAll(response)
            }

            MediatorResult.Success(
                endOfPaginationReached = false
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpRetryException) {
            MediatorResult.Error(e)
        }
    }

}