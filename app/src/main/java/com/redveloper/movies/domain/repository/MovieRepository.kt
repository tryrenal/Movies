package com.redveloper.movies.domain.repository

import com.redveloper.movies.domain.entity.Movies
import com.redveloper.movies.domain.repository.api.MoviesApi
import com.redveloper.movies.utils.RxSchedulers
import io.reactivex.Single
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val moviesApi: MoviesApi,
    private val schedulers: RxSchedulers
) {

    fun getMovies(): Single<Movies>{
        return moviesApi.getMovies()
            .subscribeOn(schedulers.network())
    }
}