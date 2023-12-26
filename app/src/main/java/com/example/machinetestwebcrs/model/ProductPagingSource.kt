package com.example.machinetestwebcrs.model
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.machinetestwebcrs.api.ApiService
import retrofit2.HttpException
import java.io.IOException

class ProductPagingSource (private val apiService: ApiService) : PagingSource<Int, DataListModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataListModel> {
        return try {
            val nextPage = params.key ?: 1
            val response = apiService.getProducts(nextPage, params.loadSize) ?: emptyList()

            LoadResult.Page(
                data = response,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (response.isEmpty()) null else nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataListModel>): Int? {
        TODO("Not yet implemented")
    }

}