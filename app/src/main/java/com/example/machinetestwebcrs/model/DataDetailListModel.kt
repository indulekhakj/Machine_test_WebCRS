package com.example.machinetestwebcrs.model

class DataDetailListModel (
    var id:Int,
    var title:String,
    var price: Double,
    var description:String,
    var category:String,
    var image:String,
    var rating:DataDetailRatingModel
)

data class DataDetailRatingModel (
    var rate:Double,
    var count:Int
)