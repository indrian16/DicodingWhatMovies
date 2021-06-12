package io.indrian.whatmovies

import android.app.Application
import io.indrian.whatmovies.di.appModule
import io.indrian.whatmovies.di.remoteModule
import io.indrian.whatmovies.di.repositoryModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class WhatMoviesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            modules(
                remoteModule,
                repositoryModule,
                appModule
            )
        }
    }
}