package io.indrian.whatmovies.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.data.source.local.dao.MovieDao
import io.indrian.whatmovies.data.source.local.dao.TVShowDao
import io.indrian.whatmovies.utils.AppConstants

@Database(
    entities = [Movie::class, TVShow::class],
    version = AppConstants.DB_VERSION,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TVShowDao
}