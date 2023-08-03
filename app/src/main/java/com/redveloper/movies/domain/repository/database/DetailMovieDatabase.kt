package com.redveloper.movies.domain.repository.database

import com.redveloper.movies.domain.entity.DetailMovie
import io.reactivex.Completable
import io.reactivex.Single

interface DetailMovieDatabase {
    fun insertDetailMovie(data: DetailMovie): Completable
    fun getDetailMovie(movieId: Int): Single<DetailMovie>
}