package io.indrian.whatmovies.utils

object AppUtils {

    fun getImagePath(path: String): String {
        return "https://image.tmdb.org/t/p/w500$path"
    }

    fun getFiveStar(average: Double): Float {
        return (average / 2).toFloat()
    }

    fun getMovieGenre(genre: Int): String {
        return when (genre) {
            28 -> "Action"
            12 -> "Adventure"
            16 -> "Animation"
            35 -> "Comedy"
            80 -> "Crime"
            99 -> "Documentary"
            18 -> "Drama"
            10751 -> "Family"
            14 -> "Fantasy"
            36 -> "History"
            27 -> "Horror"
            10402 -> "Music"
            9648 -> "Mystery"
            10749 -> "Romance"
            878 -> "Science Fiction"
            10770 -> "TV Movie"
            53 -> "Thriller"
            10752 -> "War"
            37 -> "Western"
            else -> "Genre"
        }
    }

    fun getTVShowGenre(genre: Int): String {
        return when (genre) {
            10759 -> "Action & Adventure"
            16 -> "Animation"
            35 -> "Comedy"
            80 -> "Crime"
            99 -> "Documentary"
            18 -> "Drama"
            10751 -> "Family"
            10762 -> "Kids"
            9648 -> "Mystery"
            10763 -> "News"
            10764 -> "Reality"
            10765 -> "Sci-Fi & Fantasy"
            10766 -> "Soap"
            10767 -> "Talk"
            10768 -> "War & Politics"
            37 -> "Western"
            else -> "Genre"
        }
    }
}