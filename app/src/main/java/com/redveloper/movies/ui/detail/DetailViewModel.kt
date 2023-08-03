package com.redveloper.movies.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redveloper.movies.domain.entity.DetailMovie
import com.redveloper.movies.domain.entity.ResultReview
import com.redveloper.movies.domain.usecase.GetDetailMovieUseCase
import com.redveloper.movies.domain.usecase.GetReviewMovieUseCase
import com.redveloper.movies.utils.Event
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
    private val getReviewMovieUseCase: GetReviewMovieUseCase,
): ViewModel() {

    val detailMovieEvent = MutableLiveData<Event<DetailMovie>>()
    val reviewMovieEvent = MutableLiveData<Event<List<ResultReview>>>()

    fun getDetailMovie(movieId: Int){
        getDetailMovieUseCase.execute(
            GetDetailMovieUseCase.Input(
                movieId = movieId
            ),
            GetDetailMovieUseCase.Output(
                success = {
                    Log.i("dataDetailMovie", it.toString())
                    detailMovieEvent.value = Event(it)
                }
            )
        )
    }

    fun getReviewMovie(movieId: Int){
        getReviewMovieUseCase.execute(
            GetReviewMovieUseCase.Input(movieId = movieId),
            GetReviewMovieUseCase.Output(
                success = {
                    Log.i("dataReviewMovie", it.toString())
                    reviewMovieEvent.value = Event(it)
                }
            )
        )
    }

    override fun onCleared() {
        super.onCleared()
        getDetailMovieUseCase.dispose()
        getReviewMovieUseCase.dispose()
    }
}