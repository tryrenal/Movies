package com.redveloper.movies.api.Test

import com.redveloper.movies.domain.entity.RootTestModel
import com.redveloper.movies.domain.entity.TestModel
import com.redveloper.movies.domain.repository.api.TestApi
import io.reactivex.Single
import javax.inject.Inject

class TestApiImpl @Inject constructor(
  private val testApiService: TestApiService
): TestApi {
    override fun getTestList(): Single<RootTestModel> {
        return testApiService.getListTest().map {
            val data = it.map {
                TestModel(
                    userId = it.userId,
                    id = it.id,
                    title = it.title,
                    body = it.body
                )
            }

            RootTestModel(data)
        }
    }


}