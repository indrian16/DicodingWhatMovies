package io.indrian.whatmovies.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.data.repositories.Repository
import io.indrian.whatmovies.utils.CommonState
import io.indrian.whatmovies.utils.EspressoIdlingResource
import io.indrian.whatmovies.utils.Event
import kotlinx.coroutines.launch
import timber.log.Timber

class TVShowViewModel(private val repository: Repository) : ViewModel() {

    private val _tvShowState = MutableLiveData<CommonState<List<TVShow>>>()
    val tvShowState: LiveData<CommonState<List<TVShow>>> get() = _tvShowState

    private val _eventOpenDetailTVShow = MutableLiveData<Event<Long>>()
    val eventOpenDetailTVShow: LiveData<Event<Long>> get() = _eventOpenDetailTVShow

    fun getTVShows() {
        EspressoIdlingResource.increment()
        _tvShowState.value = CommonState.Loading
        Timber.d("CommonState.Loading")
        viewModelScope.launch {
            try {
                val tvShows = repository.getTVShows(page = 1)
                if (tvShows.isNotEmpty()) {
                    _tvShowState.value = CommonState.Loaded(tvShows)
                    Timber.d("CommonState.Loaded($tvShows)")
                    EspressoIdlingResource.decrement()
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

    fun openDetailTVShow(id: Long) {
        _eventOpenDetailTVShow.value = Event(id)
    }
}