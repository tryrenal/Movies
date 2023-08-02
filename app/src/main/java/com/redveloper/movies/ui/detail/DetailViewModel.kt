package com.redveloper.movies.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redveloper.movies.domain.entity.ResultMovie
import com.redveloper.movies.domain.entity.ResultReview
import com.redveloper.movies.domain.entity.Trailer
import com.redveloper.movies.domain.usecase.GetDetailMovieUseCase
import com.redveloper.movies.domain.usecase.GetReviewMovieUseCase
import com.redveloper.movies.domain.usecase.GetTrailerUseCase
import com.redveloper.movies.utils.Event
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
    private val getReviewMovieUseCase: GetReviewMovieUseCase,
    private val getTrailerUseCase: GetTrailerUseCase
): ViewModel() {

    val detailMovieEvent = MutableLiveData<Event<ResultMovie>>()
    val reviewMovieEvent = MutableLiveData<Event<List<ResultReview>>>()
    val trailerMovieEvent = MutableLiveData<Event<Trailer>>()

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

    fun getTrailler(movieId: Int){
        getTrailerUseCase.execute(
            GetTrailerUseCase.Input(movieId = movieId),
            GetTrailerUseCase.Output(
                success = {
                    Log.i("dataTraillerMovie", it.toString())
                    trailerMovieEvent.value = Event(it)
                }
            )
        )
    }

    override fun onCleared() {
        super.onCleared()
        getDetailMovieUseCase.dispose()
        getReviewMovieUseCase.dispose()
        getTrailerUseCase.dispose()
    }
}