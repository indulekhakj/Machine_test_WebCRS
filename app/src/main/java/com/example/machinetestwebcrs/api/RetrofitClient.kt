package com.example.machinetestwebcrs.api

import android.util.Log
import com.example.machinetestwebcrs.model.DataDetailListModel
import com.example.machinetestwebcrs.model.DataDetailRatingModel
import com.example.machinetestwebcrs.model.DataListModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


object RetrofitClient{
    private const val BASE_URL = "https://fakestoreapi.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)
    val defaultApiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    suspend fun fetchData(page: Int, limit: Int): List<DataListModel> {
        return withContext(Dispatchers.IO) {
            try {
                apiService.getProducts(page, limit)
            } catch (e: Exception) {
                // Handle the exception (e.g., log, show an error message)
                emptyList()
            }
        }
    }

    suspend fun fetchProductDetail(productId: Int): DataDetailListModel {
        return withContext(Dispatchers.IO) {
            try {
                apiService.getProductDetail(productId)
            } catch (e: Exception) {
                Log.e("ex",e.toString())
                // Handle the exception (e.g., log, show an error message)
                DataDetailListModel(0,"",0.00,"","","", DataDetailRatingModel(0.00,0)) // Return a default value in case of error
            }
        }
    }
}