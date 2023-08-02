package com.redveloper.movies.domain.usecase

import android.util.Log
import com.redveloper.movies.domain.entity.Trailer
import com.redveloper.movies.domain.repository.MovieRepository
import com.redveloper.movies.utils.RxSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class GetTrailerUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val schedulers: RxSchedulers
){

    private val disposables = CompositeDisposable()

    fun execute(input: Input, output: Output){
        val disposable = movieRepository.getTrailer(
            id = input.movieId
        )
            .observeOn(schedulers.ui())
            .subscribe({
                output.success?.invoke(it)
            }, {
                Log.i("errorGetTrailer", it.message.toString())
            })
    }

    fun dispose(){
        disposables.clear()
    }

    data class Input(
        val movieId: Int
    )

    data class Output(
        val success : ((Trailer) -> Unit)? = null
    )
}