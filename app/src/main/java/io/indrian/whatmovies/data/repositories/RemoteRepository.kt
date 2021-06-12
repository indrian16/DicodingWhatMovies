package io.indrian.whatmovies.data.repositories

import io.indrian.whatmovies.data.services.MovieService
import io.indrian.whatmovies.data.services.TVShowService

class RemoteRepository(
    private val movieService: MovieService,
    private val tvShowService: TVShowService
){

    suspend fun getMovies() = movieService.getMovies()
    suspend fun getDetailMovie(id: Long) = movieService.getDetailMovie(id)

    suspend fun getTVShows() = tvShowService.getTVShows()
    suspend fun getDetailTVShow(id: Long) = tvShowService.getDetailTVShow(id)
}