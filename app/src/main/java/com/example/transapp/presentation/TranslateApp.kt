package com.example.transapp.presentation

import android.app.Application
import com.example.transapp.BuildConfig
import com.example.transapp.data.getDataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TranslateApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TranslateApp)
            modules(getDataModule(BuildConfig.API_KEY), presentationModule)
        }
    }
}