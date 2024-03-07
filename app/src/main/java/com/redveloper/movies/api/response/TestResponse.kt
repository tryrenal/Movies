package com.redveloper.movies.api.response

import com.google.gson.annotations.SerializedName

data class  TestDataResponse(
    @SerializedName("userId")
    var userId: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("body")
    var body: String
)