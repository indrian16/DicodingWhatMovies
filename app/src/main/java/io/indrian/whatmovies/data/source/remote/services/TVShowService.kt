package io.indrian.whatmovies.data.source.remote.services

import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.data.source.remote.responses.ListResponse
import io.indrian.whatmovies.utils.AppConstants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVShowService {

    @GET("${AppConstants.TV_SHOW_ENDPOINT}/popular")
    fun getTVShows(@Query("page") page: Int = 1): Call<ListResponse<TVShow>>

    @GET("${AppConstants.TV_SHOW_ENDPOINT}/{id}")
    fun getDetailTVShow(@Path("id") id: Long): Call<TVShow>
}