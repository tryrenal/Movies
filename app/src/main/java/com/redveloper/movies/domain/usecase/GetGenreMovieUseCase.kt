package com.redveloper.movies.domain.usecase

import com.redveloper.movies.domain.entity.Genre
import com.redveloper.movies.domain.repository.MovieRepository
import com.redveloper.movies.domain.usecase.base.BaseUseCase
import com.redveloper.movies.domain.usecase.base.BaseUseCaseImpl
import com.redveloper.movies.utils.RxSchedulers
import javax.inject.Inject

class GetGenreMovieUseCase @Inject constructor(
    val baseUseCaseImpl: BaseUseCaseImpl,
    private val movieRepository: MovieRepository,
    private val schedulers: RxSchedulers
): BaseUseCase(baseUseCaseImpl) {

    fun execute(output: Output){
        allowExecute(
            allow = {
                addDisposable(
                    movieRepository.getGenreMovie()
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

    data class Output(
        val success: ((List<Genre>) -> Unit)? = null,
        val error: ((String) -> Unit)? = null
    )
}