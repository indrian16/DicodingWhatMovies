package io.indrian.whatmovies.data.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.indrian.whatmovies.utils.AppConstants

@Entity(tableName = AppConstants.TABLE_TV_SHOWS, primaryKeys = ["id"])
@JsonClass(generateAdapter = true)
data class TVShow(
    @Json(name = "backdrop_path")
    var backdropPath: String? = "",
    @Json(name = "first_air_date")
    var firstAirDate: String = "",
    @Json(name = "genre_ids")
    var genreIds: List<Int> = listOf(),
    @Ignore
    @Json(name = "genres")
    var genres: List<Genre> = listOf(),
    @Json(name = "id")
    var id: Long = 0,
    @Json(name = "name")
    var name: String = "",
    @Json(name = "origin_country")
    @Ignore
    var originCountry: List<String> = listOf(),
    @Json(name = "original_language")
    var originalLanguage: String = "",
    @Json(name = "original_name")
    var originalName: String = "",
    @Json(name = "overview")
    var overview: String = "",
    @Json(name = "popularity")
    var popularity: Double = 0.0,
    @Json(name = "poster_path")
    var posterPath: String? = "",
    @Json(name = "vote_average")
    var voteAverage: Double = 0.0,
    @Json(name = "vote_count")
    var voteCount: Int = 0,
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
)