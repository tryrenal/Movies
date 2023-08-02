package com.redveloper.movies.api

import com.redveloper.movies.api.response.MoviesResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MoviesApiService{

    @GET("/3/discover/movie")
    fun getListMovies() : Single<MoviesResponse>
}