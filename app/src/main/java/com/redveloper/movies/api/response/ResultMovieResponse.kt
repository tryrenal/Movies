package com.redveloper.movies.api.response

import com.google.gson.annotations.SerializedName
import com.redveloper.movies.domain.entity.ResultMovie

data class ResultMovieResponse(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("video")
    val video: Boolean? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null
) {
    fun toResultMovie(): ResultMovie{
        return ResultMovie(
            adult = adult ?: false,
            backdropPath = backdropPath,
            id = id ?: 0,
            originalLanguange = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity ?: 0.0,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            video = video ?: false,
            voteAverage = voteAverage ?: 0.0,
            voteCount = voteCount ?: 0
        )
    }
}