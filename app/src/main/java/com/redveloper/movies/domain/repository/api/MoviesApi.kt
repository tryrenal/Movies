package com.redveloper.movies.domain.repository.api

import com.redveloper.movies.domain.entity.Movies
import io.reactivex.Single

interface MoviesApi {
    fun getMovies(): Single<Movies>
}