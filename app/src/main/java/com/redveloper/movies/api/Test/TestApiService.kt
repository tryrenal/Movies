package com.redveloper.movies.api.Test

import com.redveloper.movies.api.response.TestDataResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TestApiService{

    @GET("/posts")
    fun getListTest() : Single<List<TestDataResponse>>
}