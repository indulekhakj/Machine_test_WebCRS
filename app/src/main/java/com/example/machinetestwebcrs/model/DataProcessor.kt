package com.example.machinetestwebcrs.model

import com.example.machinetestwebcrs.api.ApiService

class DataProcessor {
    suspend fun getProductsGroupedByCategory(apiService: ApiService, page: Int, limit: Int): List<CategoryWithProducts> {
        val products = apiService.getProducts(page, limit)
        return products.groupBy { it.category }
            .map { (category, productList) -> CategoryWithProducts(category, productList) }
    }
}