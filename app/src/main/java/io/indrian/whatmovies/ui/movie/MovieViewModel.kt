package io.indrian.whatmovies.ui.movie

import androidx.lifecycle.ViewModel
import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.repositories.Repository

class MovieViewModel : ViewModel() {

    fun getMovies(): List<Movie> = Repository.getMovies()
}