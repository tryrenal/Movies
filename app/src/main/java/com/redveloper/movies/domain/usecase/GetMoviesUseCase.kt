package com.redveloper.movies.domain.usecase

import com.redveloper.movies.domain.entity.ResultMovie
import com.redveloper.movies.domain.repository.MovieRepository
import com.redveloper.movies.domain.usecase.base.BaseUseCase
import com.redveloper.movies.domain.usecase.base.BaseUseCaseImpl
import com.redveloper.movies.utils.RxSchedulers
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    baseUseCaseImpl: BaseUseCaseImpl,
    private val movieRepository: MovieRepository,
    private val schedulers: RxSchedulers
): BaseUseCase(baseUseCaseImpl) {
    private var nextPage = 1
    private var isAbleToLoadMore: Boolean = false
    private var lastInput: Input? = null
    var output: Output? = null

    fun execute(genreId: Int){
        if (isExecuting) return

        nextPage = 1
        lastInput = Input(
            page = nextPage,
            genreId = genreId
        )
        getMovies(
            Input(
                page = nextPage,
                genreId = genreId
            )
        )
    }

    fun loadMore(){
        if (!isExecuting && isAbleToLoadMore){
            lastInput?.let { input ->
                getMovies(
                    Input(
                        page = nextPage,
                        genreId = input.genreId
                    )
                )
            }
        }
    }

    private fun getMovies(input: Input){
        allowExecute(
            allow = {
                addDisposable(movieRepository
                    .getMovies(
                        page = input.page,
                        genreId = input.genreId
                    )
                    .observeOn(schedulers.ui())
                    .doOnSubscribe {
                        isExecuting = true
                    }
                    .doFinally {
                        isExecuting = false
                    }
                    .subscribe({
                        nextPage += 1
                        isAbleToLoadMore = it.totalPages >= nextPage
                        val data = it.reslutMovie ?: listOf()
                        output?.success?.invoke(data)
                    }, {
                        it.message?.let{ message ->
                            output?.error?.invoke(message)
                        }
                    }))
            },
            notInternet = {
                output?.error?.invoke("you dont have internet")
            }
        )
    }

    data class Input(
        val page: Int,
        val genreId: Int
    )

    data class Output(
        val success: ((List<ResultMovie>) -> Unit)? = null,
        val error: ((message: String) -> Unit)? = null
    )
}