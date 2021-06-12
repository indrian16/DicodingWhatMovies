package io.indrian.whatmovies.di

import io.indrian.whatmovies.ui.movie.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { MovieViewModel(get()) }
}