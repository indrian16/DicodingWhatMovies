package io.indrian.whatmovies.data.models

data class Movie(
    val id: Long = 0L,
    val backdropPath: String = "",
    val genreIds: List<Int> = listOf(),
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
)