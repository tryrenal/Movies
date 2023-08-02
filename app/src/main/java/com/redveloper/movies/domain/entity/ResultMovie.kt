package com.redveloper.movies.domain.entity

data class ResultMovie(
    val adult: Boolean = false,
    val backdropPath: String? = null,
    val id: Int = 0,
    val originalLanguange: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double = 0.0,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
)