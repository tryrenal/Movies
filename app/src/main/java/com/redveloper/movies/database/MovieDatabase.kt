package com.redveloper.movies.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.redveloper.movies.database.detail.DetailMovieDao
import com.redveloper.movies.database.detail.DetailMovieEntity


@Database(
    entities = [
        DetailMovieEntity::class
    ],
    version = 1
)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun detailMovieDao(): DetailMovieDao
}