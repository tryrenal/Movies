package com.redveloper.movies.domain.entity

import java.util.Date

data class DetailMovie(
    val id: Int = 0,
    val youtubeKey: String = "",
    val backdropPath: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val posterPath: String = "",
    val releaseDate: Date? = null,
    val status: String = "",
    val tagline: String = ""
)