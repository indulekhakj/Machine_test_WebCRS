package com.example.machinetestwebcrs.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: String,
    var title:String,
    var price: Double,
    var description:String,
    var category:String,
    var image:String
    // Add other fields as needed
)