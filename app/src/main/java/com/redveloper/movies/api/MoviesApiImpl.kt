package com.redveloper.movies.api

import com.redveloper.movies.domain.entity.Movies
import com.redveloper.movies.domain.entity.ResultMovie
import com.redveloper.movies.domain.entity.Reviews
import com.redveloper.movies.domain.entity.Trailer
import com.redveloper.movies.domain.repository.api.MoviesApi
import io.reactivex.Single
import javax.inject.Inject

class MoviesApiImpl @Inject constructor(
    private val moviesApiService: MoviesApiService
): MoviesApi {
    override fun getMovies(page: Int): Single<Movies> {
        return moviesApiService.getListMovies(page).map { it.toMovies() }
    }

    override fun getDetailMovie(id: Int): Single<ResultMovie> {
        return moviesApiService.getMovieDetail(id).map { it.toResultMovie() }
    }

    override fun getReviewMovie(id: Int): Single<Reviews> {
        return moviesApiService.getReviewsMovie(id).map { it.toReviews() }
    }

    override fun getTrailer(id: Int): Single<Trailer> {
        return moviesApiService.getTraillerMovie(id).map {
            val result = it.results
            if (!result.isNullOrEmpty()) {
                result[0].toTrailer()
            } else {
                Trailer()
            }
        }
    }
}