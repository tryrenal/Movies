package com.redveloper.movies.api

import com.redveloper.movies.domain.entity.Movies
import com.redveloper.movies.domain.repository.api.MoviesApi
import io.reactivex.Single
import javax.inject.Inject

class MoviesApiImpl @Inject constructor(
    private val moviesApiService: MoviesApiService
): MoviesApi {
    override fun getMovies(): Single<Movies> {
        return moviesApiService.getListMovies().map { it.toMovies() }
    }
}