package com.kenneth.lab4_start.data

interface PlacesRepository {
    fun getPlacesByCategory(category: Category): List<Place>
    fun getPlace(index: Int): Place
    fun getPlaceIndex(place: Place): Int


}