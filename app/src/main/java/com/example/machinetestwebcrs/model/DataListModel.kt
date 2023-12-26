package com.example.machinetestwebcrs.model

data class DataListModel (
    var id:Int,
    var title:String,
    var price: Double,
    var description:String,
    var category:String,
    var image:String,
    var rating:DataRatingModel
)

data class DataRatingModel (
    var rate:Double,
    var count:Int
)