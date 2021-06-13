package io.indrian.whatmovies.data.repositories

import io.indrian.whatmovies.data.services.MovieService
import io.indrian.whatmovies.data.services.TVShowService

class RemoteRepository(
    private val movieService: MovieService,
    private val tvShowService: TVShowService
){

    suspend fun getMovies(page: Int = 1) = movieService.getMovies(page)
    suspend fun getDetailMovie(id: Long) = movieService.getDetailMovie(id)

    suspend fun getTVShows(page: Int = 1) = tvShowService.getTVShows(page)
    suspend fun getDetailTVShow(id: Long) = tvShowService.getDetailTVShow(id)
}