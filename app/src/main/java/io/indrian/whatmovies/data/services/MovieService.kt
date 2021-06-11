package io.indrian.whatmovies.data.services

import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.responses.ListResponse
import io.indrian.whatmovies.utils.AppConstants
import retrofit2.http.GET

interface MovieService {

    @GET(AppConstants.MOVIE_ENDPOINT)
    fun getMovies(): ListResponse<Movie>
}