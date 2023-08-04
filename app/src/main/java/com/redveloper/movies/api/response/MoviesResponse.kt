package com.redveloper.movies.api.response

import com.google.gson.annotations.SerializedName
import com.redveloper.movies.domain.entity.Movies

data class MoviesResponse(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null,
    @SerializedName("results")
    val resultMovie: List<ResultMovieResponse>? = null
) {
    fun toMovies(): Movies {
        return Movies(
            page = page ?: 0,
            totalPages = totalPages ?: 0,
            totalResults = totalResults ?: 0,
            reslutMovie = resultMovie?.map { it.toResultMovie() }
        )
    }
}