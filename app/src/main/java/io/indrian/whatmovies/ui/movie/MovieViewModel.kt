package io.indrian.whatmovies.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.repositories.Repository
import io.indrian.whatmovies.utils.CommonState
import io.indrian.whatmovies.utils.Event

class MovieViewModel(private val repository: Repository) : ViewModel() {

    private val _movieState = MutableLiveData<CommonState<List<Movie>>>()
    val movieState: LiveData<CommonState<List<Movie>>> get() = _movieState

    private val _eventOpenDetailMovie = MutableLiveData<Event<Long>>()
    val eventOpenDetailMovie: LiveData<Event<Long>> get() = _eventOpenDetailMovie

    fun getMovies() = repository.getMovies()

    fun openDetailMovie(id: Long) {
        _eventOpenDetailMovie.value = Event(id)
    }
}