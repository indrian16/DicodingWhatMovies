package io.indrian.whatmovies.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TVShow(
    val id: Long = 0L,
    val backdropPath: String = "",
    val firstAirDate: String = "",
    val genreIds: List<Int> = listOf(),
    val name: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
): Parcelable