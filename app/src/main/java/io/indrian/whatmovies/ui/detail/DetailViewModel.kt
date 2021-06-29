package io.indrian.whatmovies.ui.detail

import androidx.lifecycle.ViewModel
import io.indrian.whatmovies.data.repositories.Repository

class DetailViewModel(private val repository: Repository) : ViewModel() {

    fun getDetailMovies(id: Long = 0L) = repository.getDetailMovies(id)

    fun getDetailTVShow(id: Long = 0L) = repository.getDetailTVShow(id)
}