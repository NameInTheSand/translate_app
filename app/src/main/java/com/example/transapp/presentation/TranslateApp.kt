package com.example.transapp.presentation

import android.app.Application
import com.example.transapp.data.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TranslateApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TranslateApp)
            modules(dataModule, presentationModule)
        }
    }
}