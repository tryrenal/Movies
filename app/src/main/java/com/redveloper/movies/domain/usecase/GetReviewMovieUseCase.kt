package com.redveloper.movies.domain.usecase

import android.util.Log
import com.redveloper.movies.domain.entity.ResultReview
import com.redveloper.movies.domain.repository.MovieRepository
import com.redveloper.movies.utils.RxSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class GetReviewMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val schedulers: RxSchedulers
) {

    private val disposables = CompositeDisposable()

    fun execute(input: Input, output: Output){
        val disposable = movieRepository.getReviewMovie(
            id = input.movieId
        ).observeOn(schedulers.ui())
            .subscribe({
                val data = it.result ?: listOf()
                output.success?.invoke(data)
            }, {
                Log.i("errorGetReview", it.message.toString())
            })
    }

    fun dispose(){
        disposables.clear()
    }

    data class Input(
        val movieId: Int
    )

    data class Output(
        val success: ((List<ResultReview>) -> Unit)? = null
    )
}