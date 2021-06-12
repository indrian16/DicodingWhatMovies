package io.indrian.whatmovies.ui.tvshow

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

class TVShowViewModel(private val repository: Repository) : ViewModel() {

    private val _tvShowState = MutableLiveData<CommonState<List<TVShow>>>()
    val tvShowState: LiveData<CommonState<List<TVShow>>> get() = _tvShowState

    fun getTVShows() {
        _tvShowState.value = CommonState.Loading
        Timber.d("CommonState.Loading")
        viewModelScope.launch {
            try {
                val tvShows = repository.getTVShows()
                if (tvShows.isNotEmpty()) {
                    _tvShowState.value = CommonState.Loaded(tvShows)
                    Timber.d("CommonState.Loaded($tvShows)")
                } else {
                    _tvShowState.value = CommonState.Empty
                    Timber.d("CommonState.Empty")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _tvShowState.value = CommonState.Error(e.message.toString())
                Timber.d("CommonState.Error(${e.message})")
            }
        }
    }
}