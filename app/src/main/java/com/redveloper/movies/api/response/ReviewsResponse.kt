package com.redveloper.movies.api.response

import com.google.gson.annotations.SerializedName
import com.redveloper.movies.domain.entity.Reviews

data class ReviewsResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null,
    @SerializedName("results")
    val results: List<ResultReviewResponse>
){
    fun toReviews(): Reviews{
        return Reviews(
            page = page ?: 0,
            totalPages = totalPages ?: 0,
            totalResults = totalResults ?: 0,
            id = id ?: 0,
            result = results.map { it.toResultReview() }
        )
    }
}