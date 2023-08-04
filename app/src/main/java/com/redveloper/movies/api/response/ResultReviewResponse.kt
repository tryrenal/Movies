package com.redveloper.movies.api.response

import com.google.gson.annotations.SerializedName
import com.redveloper.movies.domain.entity.ResultReview
import com.redveloper.movies.utils.convertDateStringToDate
import java.text.SimpleDateFormat
import java.util.Locale

data class ResultReviewResponse(
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("updated_at")
    val updateAt: String? = null,
    @SerializedName("url")
    val url: String? = null
){
    fun toResultReview(): ResultReview{
        return ResultReview(
            author = author,
            content = content,
            createdAt = if (!createdAt.isNullOrBlank())
                convertDateStringToDate(createdAt) else null,
            updateAt = if (!updateAt.isNullOrBlank())
                convertDateStringToDate(updateAt) else null,
            id = id,
            url = url
        )
    }
}