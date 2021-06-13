package io.indrian.whatmovies.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.repositories.Repository
import io.indrian.whatmovies.utils.CommonState
import io.indrian.whatmovies.utils.Event
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieViewModel(private val repository: Repository) : ViewModel() {

    private val _movieState = MutableLiveData<CommonState<List<Movie>>>()
    val movieState: LiveData<CommonState<List<Movie>>> get() = _movieState

    private val _eventOpenDetailMovie = MutableLiveData<Event<Long>>()
    val eventOpenDetailMovie: LiveData<Event<Long>> get() = _eventOpenDetailMovie

    fun getMovies() {
        _movieState.value = CommonState.Loading
        Timber.d("CommonState.Loading")
        viewModelScope.launch {
            try {
                val movies = repository.getMovies()
                if (movies.isNotEmpty()) {
                    _movieState.value = CommonState.Loaded(movies)
                    Timber.d("CommonState.Loaded($movies)")
                } else {
                    _movieState.value = CommonState.Empty
                    Timber.d("CommonState.Empty")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _movieState.value = CommonState.Error(e.message.toString())
                Timber.d("CommonState.Error(${e.message})")
            }
        }
    }

    fun openDetailMovie(id: Long) {
        _eventOpenDetailMovie.value = Event(id)
    }
}