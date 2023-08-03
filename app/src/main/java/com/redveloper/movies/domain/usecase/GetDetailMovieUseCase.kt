package com.redveloper.movies.domain.usecase

import com.redveloper.movies.domain.entity.DetailMovie
import com.redveloper.movies.domain.repository.MovieRepository
import com.redveloper.movies.domain.usecase.base.BaseUseCase
import com.redveloper.movies.domain.usecase.base.BaseUseCaseImpl
import com.redveloper.movies.utils.RxSchedulers
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(
    baseUseCaseImpl: BaseUseCaseImpl,
    private val movieRepository: MovieRepository,
    private val schedulers: RxSchedulers
): BaseUseCase(baseUseCaseImpl) {

    fun execute(input: Input, output: Output){
        allowExecute(
            allow = {
                addDisposable(
                    movieRepository.getDetailMovie(input.movieId)
                        .observeOn(schedulers.ui())
                        .subscribe({
                            output.success?.invoke(it)
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
        val success: ((DetailMovie) -> Unit)? = null,
        val error: ((message: String) -> Unit)? = null
    )
}