package io.indrian.whatmovies.data.source.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.indrian.whatmovies.data.models.TVShow

@Dao
interface TVShowDao {

    @Query("SELECT * FROM tv_shows")
    fun getTVShows() : DataSource.Factory<Int, TVShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TVShow::class)
    fun insertTVShows(tvShow: List<TVShow>)
}