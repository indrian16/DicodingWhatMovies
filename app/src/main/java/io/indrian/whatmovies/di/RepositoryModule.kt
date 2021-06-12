package io.indrian.whatmovies.di

import io.indrian.whatmovies.data.repositories.RemoteRepository
import io.indrian.whatmovies.data.repositories.Repository
import org.koin.dsl.module

val repositoryModule = module {

    single { RemoteRepository(get(), get()) }

    single { Repository(get()) }
}