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

    private var nextPage = 1
    private var isAbleToLoadMore: Boolean = false
    private var isExecuting: Boolean = false

    var output: Output? = null

    fun execute(){
        if (isExecuting) return

        nextPage = 1
        getMovies(nextPage)
    }

    fun loadMore(){
        if (!isExecuting && isAbleToLoadMore){
            getMovies(nextPage)
        }
    }

    private fun getMovies(page: Int){
        val disposable = movieRepository
            .getMovies(page)
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
            })
        disposables.add(disposable)
    }

    fun dispose(){
        disposables.clear()
    }

    data class Output(
        val success: ((List<ResultMovie>) -> Unit)? = null,
        val error: ((message: String) -> Unit)? = null
    )
}