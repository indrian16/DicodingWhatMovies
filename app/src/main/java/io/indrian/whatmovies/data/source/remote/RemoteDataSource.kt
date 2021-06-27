package io.indrian.whatmovies.data.source.remote

import io.indrian.whatmovies.data.source.remote.services.MovieService
import io.indrian.whatmovies.data.source.remote.services.TVShowService

class RemoteDataSource(
    private val movieService: MovieService,
    private val tvShowService: TVShowService
){

    suspend fun getMovies(page: Int = 1) = movieService.getMovies(page).results
    suspend fun getDetailMovie(id: Long) = movieService.getDetailMovie(id)

    suspend fun getTVShows(page: Int = 1) = tvShowService.getTVShows(page).results
    suspend fun getDetailTVShow(id: Long) = tvShowService.getDetailTVShow(id)
}