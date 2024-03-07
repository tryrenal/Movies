package com.redveloper.movies.domain.usecase

import com.redveloper.movies.domain.entity.TestModel
import com.redveloper.movies.domain.repository.TestRepository
import com.redveloper.movies.domain.usecase.base.BaseUseCase
import com.redveloper.movies.domain.usecase.base.BaseUseCaseImpl
import com.redveloper.movies.utils.RxSchedulers
import javax.inject.Inject

class GetTestListUseCase @Inject constructor(
    baseUseCaseImpl: BaseUseCaseImpl,
    private val repository: TestRepository,
    private val schedulers: RxSchedulers
): BaseUseCase(baseUseCaseImpl) {

    var output: Output? = null

    fun execute(){
        allowExecute(
            allow = {
                addDisposable(repository
                    .getListPost()
                    .observeOn(schedulers.ui())
                    .subscribe({
                        val data = it.data
                        output?.success?.invoke(data)
                    }, { error ->
                        output?.error?.invoke(error.message.toString())
                    }))
            },
            notInternet = {
                output?.error?.invoke("no internet")
            }
        )
    }

    data class Output(
        val success: ((List<TestModel>) -> Unit)? = null,
        val error: ((String) -> Unit)? = null
    )
}