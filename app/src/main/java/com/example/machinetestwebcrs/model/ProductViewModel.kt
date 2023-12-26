package com.example.machinetestwebcrs.model
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.machinetestwebcrs.api.ApiService
import com.example.machinetestwebcrs.api.RetrofitClient
import kotlinx.coroutines.flow.Flow

class ProductViewModel(private val apiService: ApiService) : ViewModel() {


    constructor() : this(RetrofitClient.defaultApiService) {

    }

    fun getProducts(): Flow<PagingData<DataListModel>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { ProductPagingSource(apiService) }
        ).flow
    }

    companion object {
        private const val PAGE_SIZE = 10
    }
}