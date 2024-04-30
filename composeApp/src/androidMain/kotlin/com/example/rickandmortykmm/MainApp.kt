package com.example.rickandmortykmm

import android.app.Application
import di.dataSourceModule
import di.networkModule
import di.repositoryModule
import di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApp)
            androidLogger()
            modules(networkModule, repositoryModule, dataSourceModule, viewModelModule)
        }
    }
}
