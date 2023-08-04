package com.redveloper.movies.domain.repository.database

import com.redveloper.movies.domain.entity.Genre
import io.reactivex.Completable
import io.reactivex.Single

interface GenreDatabase {
    fun insertGenres(data: List<Genre>): Completable
    fun getGenres(): Single<List<Genre>>
}