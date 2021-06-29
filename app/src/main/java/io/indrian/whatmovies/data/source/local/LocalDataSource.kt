package io.indrian.whatmovies.data.source.local

import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.data.source.local.dao.MovieDao
import io.indrian.whatmovies.data.source.local.dao.TVShowDao

class LocalDataSource(
    private val movieDao: MovieDao,
    private val tvShowDao: TVShowDao
) {

    fun getMovies() = movieDao.getMovies()
    fun getMovie(id: Long) = movieDao.getMovie(id)
    fun insertMovies(movies: List<Movie>) = movieDao.insertMovies(movies)
    fun updateMovie(movie: Movie) = movieDao.updateMovie(movie)
    fun getFavoriteMovies() = movieDao.getFavoriteMovies()

    fun getTVShows() = tvShowDao.getTVShows()
    fun getTVShow(id: Long) = tvShowDao.getTVShow(id)
    fun insertTVShow(tvShows: List<TVShow>) = tvShowDao.insertTVShows(tvShows)
    fun updateTVShow(tvShow: TVShow) = tvShowDao.updateTVShow(tvShow)
    fun getFavoriteTVShows() = tvShowDao.getFavoriteTVShows()
}