package com.redveloper.movies.domain.usecase

import com.redveloper.movies.domain.entity.ResultMovie
import com.redveloper.movies.domain.repository.MovieRepository
import com.redveloper.movies.utils.RxSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val schedulers: RxSchedulers
) {
    private val disposables = CompositeDisposable()

    var output: Output? = null

    fun execute(){
        val disposable = movieRepository.getMovies()
            .observeOn(schedulers.ui())
            .subscribe({
                val data = it.reslutMovie ?: listOf()
                output?.success?.invoke(data)
            }, {

            })
        disposables.add(disposable)
    }

    fun dispose(){
        disposables.clear()
    }

    data class Output(
        val success: ((List<ResultMovie>) -> Unit)? = null
    )
}