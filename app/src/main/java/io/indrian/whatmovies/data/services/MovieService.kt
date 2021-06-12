package io.indrian.whatmovies.data.services

import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.responses.ListResponse
import io.indrian.whatmovies.utils.AppConstants
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET("${AppConstants.MOVIE_ENDPOINT}/popular")
    suspend fun getMovies(): ListResponse<Movie>

    @GET("${AppConstants.MOVIE_ENDPOINT}/{id}")
    suspend fun getDetailMovie(@Path("id") id: Long): Movie?
}