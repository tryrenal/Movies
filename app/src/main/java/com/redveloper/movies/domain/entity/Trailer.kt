package com.redveloper.movies.domain.entity

import java.util.Date

data class Trailer(
    val type: String? = null,
    val official: Boolean = false,
    val site: String? = null,
    val key: String? = null,
    val publishedAt: Date? = null
)