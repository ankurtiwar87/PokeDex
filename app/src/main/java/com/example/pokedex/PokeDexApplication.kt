package com.example.pokedex

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class PokeDexApplication:Application(){

    override fun onCreate() {
        super.onCreate()

        Timber.plant(DebugTree())
    }
}