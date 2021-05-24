package io.indrian.whatmovies.ui.detail

import androidx.lifecycle.ViewModel
import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.data.repositories.Repository

class DetailViewModel : ViewModel() {

    fun getDetailMovies(id: Long): Movie? = Repository.getDetailMovies(id)

    fun getDetailTVShow(id: Long): TVShow? = Repository.getDetailTVShow(id)
}