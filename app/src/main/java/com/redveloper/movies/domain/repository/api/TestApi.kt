package com.redveloper.movies.domain.repository.api

import com.redveloper.movies.domain.entity.RootTestModel
import com.redveloper.movies.domain.entity.TestModel
import io.reactivex.Single

interface TestApi {
    fun getTestList(): Single<RootTestModel>
}