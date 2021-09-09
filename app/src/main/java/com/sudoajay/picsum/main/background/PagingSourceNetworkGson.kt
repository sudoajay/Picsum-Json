package com.sudoajay.picsum.main.background

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sudoajay.picsum.main.api.PicsumApiInterface
import com.sudoajay.picsum.main.model.PersonGson
import com.sudoajay.picsum.main.model.PersonJackson
import retrofit2.HttpException
import java.io.IOException

class PagingSourceNetworkGson(
    private val picsumApiInterface: PicsumApiInterface
) : PagingSource<Int, PersonGson>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PersonGson> {
        //for first case it will be null, then we can pass some default value, in our case it's 1
        val position = params.key ?: 1
        return try {
            val response = picsumApiInterface.getPersonGsonPaging(position, params.loadSize)

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = null
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, PersonGson>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
