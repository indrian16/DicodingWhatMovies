package io.indrian.whatmovies.data.repositories

import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.data.source.remote.RemoteDataSource

class Repository(
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getMovies(page: Int = 1): List<Movie> = remoteDataSource.getMovies(page)

    suspend fun getTVShows(page: Int = 1): List<TVShow> = remoteDataSource.getTVShows(page)

    suspend fun getDetailMovies(id: Long): Movie? = remoteDataSource.getDetailMovie(id)

    suspend fun getDetailTVShow(id: Long): TVShow? = remoteDataSource.getDetailTVShow(id)
}