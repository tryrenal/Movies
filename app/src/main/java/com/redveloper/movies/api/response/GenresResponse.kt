package com.redveloper.movies.api.response

import com.google.gson.annotations.SerializedName
import com.redveloper.movies.domain.entity.Genre

data class GenresResponse(
    @SerializedName("genres")
    val genres: List<DataGenreResponse>
)

data class DataGenreResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
){
    fun toGenre(): Genre {
        return Genre(
            id = id,
            name = name
        )
    }
}