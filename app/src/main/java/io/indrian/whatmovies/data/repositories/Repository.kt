package io.indrian.whatmovies.data.repositories

import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.models.TVShow

class Repository(
    private val remoteRepository: RemoteRepository
) {

    suspend fun getMovies(): List<Movie> = remoteRepository.getMovies().results

    suspend fun getTVShows(): List<TVShow> = remoteRepository.getTVShows().results

    suspend fun getDetailMovies(id: Long): Movie? = remoteRepository.getDetailMovie(id)

    suspend fun getDetailTVShow(id: Long): TVShow = remoteRepository.getDetailTVShow(id)
}