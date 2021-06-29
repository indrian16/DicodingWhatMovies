package io.indrian.whatmovies.di

import io.indrian.whatmovies.ui.detail.DetailViewModel
import io.indrian.whatmovies.ui.favoritemovies.FavoritesMovieViewModel
import io.indrian.whatmovies.ui.movie.MovieViewModel
import io.indrian.whatmovies.ui.tvshow.TVShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { MovieViewModel(get()) }
    viewModel { TVShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavoritesMovieViewModel(get()) }
}