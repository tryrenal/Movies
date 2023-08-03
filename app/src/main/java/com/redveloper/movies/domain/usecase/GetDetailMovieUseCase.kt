package com.redveloper.movies.domain.usecase

import android.util.Log
import com.redveloper.movies.domain.entity.DetailMovie
import com.redveloper.movies.domain.entity.ResultMovie
import com.redveloper.movies.domain.repository.MovieRepository
import com.redveloper.movies.utils.RxSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val schedulers: RxSchedulers
) {

    private val disposables = CompositeDisposable()

    fun execute(input: Input, output: Output){
        val disposable = movieRepository.getDetailMovie(input.movieId)
            .observeOn(schedulers.ui())
            .subscribe({
                output.success?.invoke(it)
            }, {
                it.message?.let { message ->
                    output.error?.invoke(message)
                }
            })
        disposables.add(disposable)
    }

    fun dispose(){
        disposables.clear()
    }

    data class Input(
        val movieId: Int
    )

    data class Output(
        val success: ((DetailMovie) -> Unit)? = null,
        val error: ((message: String) -> Unit)? = null
    )
}