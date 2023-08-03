package com.redveloper.movies.domain.usecase

import com.redveloper.movies.domain.entity.ResultReview
import com.redveloper.movies.domain.repository.MovieRepository
import com.redveloper.movies.domain.usecase.base.BaseUseCase
import com.redveloper.movies.domain.usecase.base.BaseUseCaseImpl
import com.redveloper.movies.utils.RxSchedulers
import javax.inject.Inject

class GetReviewMovieUseCase @Inject constructor(
    baseUseCase: BaseUseCaseImpl,
    private val movieRepository: MovieRepository,
    private val schedulers: RxSchedulers
): BaseUseCase(baseUseCase) {

    fun execute(input: Input, output: Output){
        allowExecute(
            allow = {
                addDisposable(
                    movieRepository.getReviewMovie(
                        id = input.movieId
                    ).observeOn(schedulers.ui())
                        .subscribe({
                            val data = it.result ?: listOf()
                            output.success?.invoke(data)
                        }, {
                            it.message?.let { message ->
                                output.error?.invoke(message)
                            }
                        })
                )
            },
            notInternet = {
                output.error?.invoke("you dont have internet")
            }
        )
    }

    data class Input(
        val movieId: Int
    )

    data class Output(
        val success: ((List<ResultReview>) -> Unit)? = null,
        val error: ((message: String) -> Unit)? = null
    )
}