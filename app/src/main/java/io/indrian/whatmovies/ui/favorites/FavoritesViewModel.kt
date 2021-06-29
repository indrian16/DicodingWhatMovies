package io.indrian.whatmovies.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.data.repositories.Repository
import io.indrian.whatmovies.utils.Event

class FavoritesViewModel(private val repository: Repository) : ViewModel() {

    private val _eventOpenDetailMovie = MutableLiveData<Event<Long>>()
    val eventOpenDetailMovie: LiveData<Event<Long>> get() = _eventOpenDetailMovie

    private val _eventOpenDetailTVShow = MutableLiveData<Event<Long>>()
    val eventOpenDetailTVShow: LiveData<Event<Long>> get() = _eventOpenDetailTVShow

    fun openDetailMovie(id: Long) {
        _eventOpenDetailMovie.value = Event(id)
    }

    fun openDetailTVShow(id: Long) {
        _eventOpenDetailTVShow.value = Event(id)
    }

    fun getFavoriteMovies() = repository.getFavoriteMovies()
    fun getFavoritesTVShows() = repository.getFavoriteTVShows()

    fun setFavoriteMovie(movie: Movie) = repository.updateMovie(movie)
    fun setFavoriteTVShow(tvShow: TVShow) = repository.updateTVShow(tvShow)
}