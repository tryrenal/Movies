package com.redveloper.movies.api

import com.redveloper.movies.api.response.MoviesResponse
import com.redveloper.movies.api.response.ResultMovieResponse
import com.redveloper.movies.api.response.ReviewsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApiService{

    @GET("/3/discover/movie")
    fun getListMovies() : Single<MoviesResponse>

    @GET("3/movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") id: Int
    ): Single<ResultMovieResponse>

    @GET("/3/movie/{movie_id}/reviews")
    fun getReviewsMovie(
        @Path("movie_id") id: Int
    ): Single<ReviewsResponse>
}