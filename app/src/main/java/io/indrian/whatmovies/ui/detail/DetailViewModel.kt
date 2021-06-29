package io.indrian.whatmovies.ui.detail

import androidx.lifecycle.ViewModel
import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.data.repositories.Repository

class DetailViewModel(private val repository: Repository) : ViewModel() {

    fun getDetailMovies(id: Long = 0L) = repository.getDetailMovies(id)

    fun getDetailTVShow(id: Long = 0L) = repository.getDetailTVShow(id)

    fun setFavoriteMovie(movie: Movie) = repository.updateMovie(movie)

    fun setFavoriteTVShow(tvShow: TVShow) = repository.updateTVShow(tvShow)
}