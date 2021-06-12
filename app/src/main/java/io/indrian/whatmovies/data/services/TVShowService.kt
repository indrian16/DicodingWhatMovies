package io.indrian.whatmovies.data.services

import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.data.responses.ListResponse
import io.indrian.whatmovies.utils.AppConstants
import retrofit2.http.GET

interface TVShowService {

    @GET(AppConstants.TV_SHOW_ENDPOINT)
    suspend fun getTVShows(): ListResponse<TVShow>
}