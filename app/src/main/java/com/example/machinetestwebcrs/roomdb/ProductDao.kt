package com.example.machinetestwebcrs.roomdb

import androidx.paging.PagingData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: PagingData<ProductEntity>)
   /* @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<DataListModel>)*/
    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<ProductEntity>>
}