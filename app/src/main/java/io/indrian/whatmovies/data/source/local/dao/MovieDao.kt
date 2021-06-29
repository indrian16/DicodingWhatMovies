package io.indrian.whatmovies.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import io.indrian.whatmovies.data.models.Movie


@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getMovies() : DataSource.Factory<Int, Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = Movie::class)
    fun insertMovies(movies: List<Movie>)

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovie(id: Long): LiveData<Movie>

    @Update
    fun updateMovie(movie: Movie)
}