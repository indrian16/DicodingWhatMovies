package io.indrian.whatmovies.data.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListResponse<T>(
    @Json(name = "page")
    val page: Int = 0,
    @Json(name = "results")
    val results: List<T> = listOf(),
    @Json(name = "total_pages")
    val totalPages: Int = 0,
    @Json(name = "total_results")
    val totalResults: Int = 0
)