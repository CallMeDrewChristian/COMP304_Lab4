package com.kenneth.lab4_start.data

data class Place (
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val image: Int,
    val category: Category

)

//might need to change when changing for a different place or add on
enum class Category {
    Landmark,
    Park,
    Museum,
    Shopping
}

