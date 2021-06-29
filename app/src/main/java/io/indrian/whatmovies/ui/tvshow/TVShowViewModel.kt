package io.indrian.whatmovies.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.data.repositories.Repository
import io.indrian.whatmovies.utils.CommonState
import io.indrian.whatmovies.utils.Event

class TVShowViewModel(private val repository: Repository) : ViewModel() {

    private val _tvShowState = MutableLiveData<CommonState<List<TVShow>>>()
    val tvShowState: LiveData<CommonState<List<TVShow>>> get() = _tvShowState

    private val _eventOpenDetailTVShow = MutableLiveData<Event<Long>>()
    val eventOpenDetailTVShow: LiveData<Event<Long>> get() = _eventOpenDetailTVShow

    fun getTVShows() = repository.getTVShows()

    fun setFavorite(tvShow: TVShow) = repository.updateTVShow(tvShow)

    fun openDetailTVShow(id: Long) {
        _eventOpenDetailTVShow.value = Event(id)
    }
}