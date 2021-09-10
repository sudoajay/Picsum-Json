package com.sudoajay.picsum.main.background.remoteMediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.sudoajay.picsum.main.api.PicsumApiInterface
import com.sudoajay.picsum.main.database.jackson.PersonLocalJacksonDatabase
import com.sudoajay.picsum.main.database.jackson.PersonLocalJacksonRepository
import com.sudoajay.picsum.main.model.local.PersonLocalJackson
import java.io.IOException
import java.net.HttpRetryException

//
@OptIn(ExperimentalPagingApi::class)
class RemoteMediatorJackson(
    private val database: PersonLocalJacksonDatabase,
    private val personLocalJacksonRepository: PersonLocalJacksonRepository,
    private val picsumApiInterface: PicsumApiInterface
) : RemoteMediator<Int, PersonLocalJackson>() {


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PersonLocalJackson>
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
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )

                    // You must explicitly check if the last item is null when
                    // appending, since passing null to networkService is only
                    // valid for initial load. If lastItem is null it means no
                    // items were loaded after the initial REFRESH and there are
                    // no more items to load.

                    lastItem.id
                }
            }

            // Suspending network load via Retrofit. This doesn't need to be
            // wrapped in a withContext(Dispatcher.IO) { ... } block since
            // Retrofit's Coroutine CallAdapter dispatches on a worker
            // thread.
            val response = picsumApiInterface.getLocalPersonJacksonPaging(1,  10)
            database.withTransaction {

                // Insert new users into database, which invalidates the
                // current PagingData, allowing Paging to present the updates
                // in the DB.
                personLocalJacksonRepository.insertAll(response)
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