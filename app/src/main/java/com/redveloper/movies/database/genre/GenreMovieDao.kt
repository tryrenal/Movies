package com.redveloper.movies.database.genre

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface GenreMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenres(data: List<GenreMovieEntity>): Completable

    @Query("SELECT * FROM genre_movie")
    fun getGenres(): Single<List<GenreMovieEntity>>
}