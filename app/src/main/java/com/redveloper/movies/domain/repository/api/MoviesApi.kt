package com.redveloper.movies.domain.repository.api

import com.redveloper.movies.domain.entity.Movies
import com.redveloper.movies.domain.entity.ResultMovie
import com.redveloper.movies.domain.entity.Reviews
import io.reactivex.Single

interface MoviesApi {
    fun getMovies(): Single<Movies>
    fun getDetailMovie(id: Int): Single<ResultMovie>
    fun getReviewMovie(id: Int): Single<Reviews>
}