package com.redveloper.movies.domain.entity

import java.util.Date

data class DetailMovie(
    val id: Int,
    val youtubeKey: String,
    val backdropPath: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: Date,
    val status: String,
    val tagline: String
)