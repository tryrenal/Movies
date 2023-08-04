package com.redveloper.movies.database.detail

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface DetailMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailMovie(data: DetailMovieEntity): Completable

    @Query("SELECT * FROM detail_movie WHERE id = :movieId")
    fun getDetailMovieById(movieId: Int): Single<DetailMovieEntity>
}