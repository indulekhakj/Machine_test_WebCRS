package com.example.machinetestwebcrs.model
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.machinetestwebcrs.api.ApiService
import com.example.machinetestwebcrs.api.RetrofitClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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