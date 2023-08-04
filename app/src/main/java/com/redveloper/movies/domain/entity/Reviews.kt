package com.redveloper.movies.domain.entity

data class Reviews(
    val id: Int = 0,
    val page: Int = 0,
    val totalPages: Int = 0,
    val totalResults: Int = 0,
    val result: List<ResultReview>? = null
)