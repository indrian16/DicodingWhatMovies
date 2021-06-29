package io.indrian.whatmovies.data.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.indrian.whatmovies.utils.AppConstants

@Entity(tableName = AppConstants.TABLE_MOVIE, primaryKeys = ["id"])
@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "adult")
    var adult: Boolean = false,
    @Json(name = "backdrop_path")
    var backdropPath: String? = "",
    @Json(name = "genre_ids")
    var genreIds: List<Int> = listOf(),
    @Ignore
    @Json(name = "genres")
    var genres: List<Genre> = listOf(),
    @Json(name = "id")
    var id: Long = 0,
    @Json(name = "original_language")
    var originalLanguage: String = "",
    @Json(name = "original_title")
    var originalTitle: String = "",
    @Json(name = "overview")
    var overview: String = "",
    @Json(name = "popularity")
    var popularity: Double = 0.0,
    @Json(name = "poster_path")
    var posterPath: String? = "",
    @Json(name = "release_date")
    var releaseDate: String = "",
    @Json(name = "title")
    var title: String = "",
    @Json(name = "video")
    var video: Boolean = false,
    @Json(name = "vote_average")
    var voteAverage: Double = 0.0,
    @Json(name = "vote_count")
    var voteCount: Int = 0,
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
)