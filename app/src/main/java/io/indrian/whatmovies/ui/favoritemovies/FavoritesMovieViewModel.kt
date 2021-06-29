package io.indrian.whatmovies.ui.favoritemovies

import androidx.lifecycle.ViewModel
import io.indrian.whatmovies.data.repositories.Repository

class FavoritesMovieViewModel(private val repository: Repository) : ViewModel() {

    fun getFavoriteMovies() = repository.getFavoriteMovies()
}