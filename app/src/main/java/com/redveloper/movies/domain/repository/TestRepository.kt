package com.redveloper.movies.domain.repository

import com.redveloper.movies.domain.entity.RootTestModel
import com.redveloper.movies.domain.repository.api.TestApi
import com.redveloper.movies.utils.RxSchedulers
import io.reactivex.Single
import javax.inject.Inject

class TestRepository @Inject constructor(
    private val api: TestApi,
    private val scheduler: RxSchedulers
) {

    fun getListPost(): Single<RootTestModel>{
        return api.getTestList().subscribeOn(scheduler.network())
    }
}