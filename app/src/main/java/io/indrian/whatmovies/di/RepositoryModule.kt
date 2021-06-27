package io.indrian.whatmovies.di

import io.indrian.whatmovies.data.source.remote.RemoteDataSource
import io.indrian.whatmovies.data.repositories.Repository
import org.koin.dsl.module

val repositoryModule = module {

    single { RemoteDataSource(get(), get()) }

    single { Repository(get()) }
}