package io.indrian.whatmovies.di

import android.content.Context
import androidx.room.Room
import io.indrian.whatmovies.data.source.local.AppDatabase
import io.indrian.whatmovies.data.source.local.LocalDataSource
import io.indrian.whatmovies.utils.AppConstants
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {

    single {
        createDB(androidContext())
    }

    factory { get<AppDatabase>().movieDao() }
    factory { get<AppDatabase>().tvShowDao() }

    single { LocalDataSource(get(), get()) }
}

fun createDB(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        AppConstants.DB_NAME
    ).build()
}