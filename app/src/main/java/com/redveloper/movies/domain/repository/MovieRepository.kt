package com.redveloper.movies.domain.repository

import com.redveloper.movies.domain.entity.Movies
import com.redveloper.movies.domain.entity.ResultMovie
import com.redveloper.movies.domain.entity.Reviews
import com.redveloper.movies.domain.entity.Trailer
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

    fun getDetailMovie(id: Int): Single<ResultMovie>{
        return moviesApi.getDetailMovie(id)
            .subscribeOn(schedulers.network())
    }

    fun getReviewMovie(id: Int): Single<Reviews>{
        return moviesApi.getReviewMovie(id)
            .subscribeOn(schedulers.network())
    }

    fun getTrailer(id: Int): Single<Trailer>{
        return moviesApi.getTrailer(id)
            .subscribeOn(schedulers.network())
    }
}