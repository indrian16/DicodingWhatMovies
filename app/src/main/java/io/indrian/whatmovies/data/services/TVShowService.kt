package io.indrian.whatmovies.data.services

import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.data.responses.ListResponse
import io.indrian.whatmovies.utils.AppConstants
import retrofit2.http.GET
import retrofit2.http.Path

interface TVShowService {

    @GET("${AppConstants.TV_SHOW_ENDPOINT}/popular")
    suspend fun getTVShows(): ListResponse<TVShow>

    @GET("${AppConstants.TV_SHOW_ENDPOINT}/{id}")
    suspend fun getDetailTVShow(@Path("id") id: Long): TVShow
}