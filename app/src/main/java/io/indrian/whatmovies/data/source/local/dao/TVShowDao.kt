package io.indrian.whatmovies.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import io.indrian.whatmovies.data.models.TVShow

@Dao
interface TVShowDao {

    @Query("SELECT * FROM tv_shows")
    fun getTVShows() : DataSource.Factory<Int, TVShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TVShow::class)
    fun insertTVShows(tvShow: List<TVShow>)

    @Query("SELECT * FROM tv_shows WHERE id = :id")
    fun getTVShow(id: Long): LiveData<TVShow>

    @Update
    fun updateTVShow(tvShow: TVShow)
}