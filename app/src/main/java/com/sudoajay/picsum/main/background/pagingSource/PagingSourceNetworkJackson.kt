package com.sudoajay.picsum.main.background.pagingSource

import android.content.Context
import android.widget.Toast
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sudoajay.picsum.R
import com.sudoajay.picsum.helper.Toaster
import com.sudoajay.picsum.main.api.PicsumApiInterface
import com.sudoajay.picsum.main.model.remote.PersonJackson
import retrofit2.HttpException
import java.io.IOException

class PagingSourceNetworkJackson(
    private val picsumApiInterface: PicsumApiInterface,private val context: Context
) : PagingSource<Int, PersonJackson>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PersonJackson> {
        //for first case it will be null, then we can pass some default value, in our case it's 1
        val position = params.key ?: 1
        return try {
            val response = picsumApiInterface.getPersonJacksonPaging(position, params.loadSize)

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = null
            )

        } catch (exception: IOException) {
            Toaster.showToast(context,context.getString(R.string.somethingWentWrong_text))
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            Toaster.showToast(context,context.getString(R.string.noInternetConnection_text))

            return LoadResult.Error(exception)
        }
    }



    override fun getRefreshKey(state: PagingState<Int, PersonJackson>): Int? {
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
