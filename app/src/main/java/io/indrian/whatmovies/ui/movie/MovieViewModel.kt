package io.indrian.whatmovies.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.repositories.Repository
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: Repository) : ViewModel() {

    fun getMovies() {
        viewModelScope.launch {
            try {
                val movies = repository.getMovies()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}