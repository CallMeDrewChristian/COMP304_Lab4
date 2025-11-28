package com.kenneth.lab4_start.viewModel

import androidx.lifecycle.ViewModel
import com.kenneth.lab4_start.data.Category
import com.kenneth.lab4_start.data.Place
import com.kenneth.lab4_start.data.PlacesRepository

class PlacesViewModel(
    private val placesRepository: PlacesRepository
) : ViewModel() {

    fun getPlacesByCategory(category: Category): List<Place> = placesRepository.getPlacesByCategory(category)
    fun getPlace(index: Int): Place = placesRepository.getPlace(index)
    fun getCategories(): List<String> = Category.entries.map {it.name}
    fun getPlaceIndex(place: Place): Int = placesRepository.getPlaceIndex(place)
}