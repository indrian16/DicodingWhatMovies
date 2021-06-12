package io.indrian.whatmovies.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.data.repositories.Repository
import io.indrian.whatmovies.utils.CommonState
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailViewModel(private val repository: Repository) : ViewModel() {

    private val _stateDetailMovie = MutableLiveData<CommonState<Movie>>()
    val stateDetailMovie: LiveData<CommonState<Movie>> get() = _stateDetailMovie

    private val _stateDetailTVShow = MutableLiveData<CommonState<TVShow>>()
    val stateDetailTVShow: LiveData<CommonState<TVShow>> get() = _stateDetailTVShow

    fun getDetailMovies(id: Long) {
        _stateDetailMovie.value = CommonState.Loading
        Timber.d("CommonState.Loading")
        viewModelScope.launch {
            try {
                val movies = repository.getDetailMovies(id)
                if (movies != null) {
                    _stateDetailMovie.value = CommonState.Loaded(movies)
                    Timber.d("CommonState.Loaded($movies)")
                } else {
                    _stateDetailMovie.value = CommonState.Empty
                    Timber.d("CommonState.Empty")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _stateDetailMovie.value = CommonState.Error(e.message.toString())
                Timber.d("CommonState.Error(${e.message})")
            }
        }
    }

    fun getDetailTVShow(id: Long) {
        _stateDetailTVShow.value = CommonState.Loading
        Timber.d("CommonState.Loading")
        viewModelScope.launch {
            try {
                val tvShow = repository.getDetailTVShow(id)
                if (tvShow != null) {
                    _stateDetailTVShow.value = CommonState.Loaded(tvShow)
                    Timber.d("CommonState.Loaded($tvShow)")
                } else {
                    _stateDetailTVShow.value = CommonState.Empty
                    Timber.d("CommonState.Empty")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _stateDetailTVShow.value = CommonState.Error(e.message.toString())
                Timber.d("CommonState.Error(${e.message})")
            }
        }
    }
}