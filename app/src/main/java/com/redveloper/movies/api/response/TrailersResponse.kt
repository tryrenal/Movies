package com.redveloper.movies.api.response

import com.google.gson.annotations.SerializedName

data class TrailersResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("results")
    val results: List<ResultTrailerResponse>? = null
)