package com.kenneth.lab4_start.ui.views

import android.app.Application
import com.kenneth.lab4_start.di.appModules
import org.koin.core.context.startKoin

class PlacesApplication(): Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(appModules)
        }
    }
}