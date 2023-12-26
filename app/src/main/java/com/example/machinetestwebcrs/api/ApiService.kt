package com.example.machinetestwebcrs.api

import com.example.machinetestwebcrs.model.DataDetailListModel
import com.example.machinetestwebcrs.model.DataListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun getProducts(@Query("page") page: Int, @Query("limit") limit: Int): List<DataListModel>


    @GET("products/{id}")
    suspend fun getProductDetail(@Path("id") productId: Int): DataDetailListModel

}