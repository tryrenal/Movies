package com.redveloper.movies.api.response

import com.google.gson.annotations.SerializedName
import com.redveloper.movies.domain.entity.Trailer
import com.redveloper.movies.utils.convertDateStringToDate

data class ResultTrailerResponse(
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("official")
    val official: Boolean = false,
    @SerializedName("site")
    val site: String? = null,
    @SerializedName("key")
    val key: String? = null,
    @SerializedName("published_at")
    val publishedAt: String? = null
) {
    fun toTrailer(): Trailer{
        return Trailer(
            type = type,
            official = official,
            site = site,
            key = key,
            publishedAt = if (!publishedAt.isNullOrBlank())
                convertDateStringToDate(publishedAt) else null
        )
    }
}