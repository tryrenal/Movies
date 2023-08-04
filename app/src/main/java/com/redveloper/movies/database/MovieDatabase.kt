package com.redveloper.movies.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.redveloper.movies.database.detail.DetailMovieDao
import com.redveloper.movies.database.detail.DetailMovieEntity
import com.redveloper.movies.database.genre.GenreMovieDao
import com.redveloper.movies.database.genre.GenreMovieEntity


@Database(
    entities = [
        DetailMovieEntity::class,
        GenreMovieEntity::class
    ],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun detailMovieDao(): DetailMovieDao

    abstract fun genreMovieDao(): GenreMovieDao

}