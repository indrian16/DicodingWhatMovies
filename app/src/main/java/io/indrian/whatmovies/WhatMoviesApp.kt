package io.indrian.whatmovies

import android.app.Application
import timber.log.Timber

class WhatMoviesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}