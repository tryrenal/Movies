package com.redveloper.movies.domain.entity

data class Movies(
    val page: Int = 0,
    val totalPages: Int = 0,
    val totalResults: Int = 0,
    val reslutMovie: List<ResultMovie>? = null
)