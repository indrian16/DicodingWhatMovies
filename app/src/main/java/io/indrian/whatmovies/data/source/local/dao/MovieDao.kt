package io.indrian.whatmovies.data.source.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.indrian.whatmovies.data.models.Movie


@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getMovies() : DataSource.Factory<Int, Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = Movie::class)
    fun insertMovies(movies: List<Movie>)
}