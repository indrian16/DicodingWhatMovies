package io.indrian.whatmovies.data.source.remote.services

import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.responses.ListResponse
import io.indrian.whatmovies.utils.AppConstants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("${AppConstants.MOVIE_ENDPOINT}/popular")
    suspend fun getMovies(@Query("page") page: Int = 1): ListResponse<Movie>

    @GET("${AppConstants.MOVIE_ENDPOINT}/{id}")
    suspend fun getDetailMovie(@Path("id") id: Long): Movie?
}