package com.kenneth.lab4_start.data

class PlacesRepositoryImpl: PlacesRepository {
    override fun getPlacesByCategory(category: Category): List<Place> {
        return places.filter {it.category == category}
    }

    override fun getPlace(index: Int): Place {
        val placeIndex = if(index > places.size - 1) 0
        else index
        return places[placeIndex]
    }

    override fun getPlaceIndex(place: Place): Int {
        return places.indexOf(place)
    }
}