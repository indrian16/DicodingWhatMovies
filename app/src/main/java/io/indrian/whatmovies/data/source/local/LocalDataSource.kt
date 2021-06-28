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
    fun insertMovies(movies: List<Movie>) = movieDao.insertMovies(movies)

    fun getTVShows() = tvShowDao.getTVShows()
    fun insertTVShow(tvShows: List<TVShow>) = tvShowDao.insertTVShows(tvShows)
}