package com.kenneth.lab4_start.di

import com.kenneth.lab4_start.data.PlacesRepository
import com.kenneth.lab4_start.data.PlacesRepositoryImpl
import com.kenneth.lab4_start.viewModel.PlacesViewModel
import org.koin.dsl.module

val appModules = module {
    single <PlacesRepository> { PlacesRepositoryImpl() }
    single { PlacesViewModel(get()) }
}