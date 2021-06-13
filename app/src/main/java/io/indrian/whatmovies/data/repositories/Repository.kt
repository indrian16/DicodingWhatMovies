package io.indrian.whatmovies.data.repositories

import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.models.TVShow

class Repository(
    private val remoteRepository: RemoteRepository
) {

    suspend fun getMovies(page: Int = 1): List<Movie> = remoteRepository.getMovies(page).results

    suspend fun getTVShows(page: Int = 1): List<TVShow> = remoteRepository.getTVShows(page).results

    suspend fun getDetailMovies(id: Long): Movie? = remoteRepository.getDetailMovie(id)

    suspend fun getDetailTVShow(id: Long): TVShow? = remoteRepository.getDetailTVShow(id)
}