package com.redveloper.movies.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redveloper.movies.domain.entity.ResultMovie
import com.redveloper.movies.domain.usecase.GetDetailMovieUseCase
import com.redveloper.movies.utils.Event
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getDetailMovieUseCase: GetDetailMovieUseCase
): ViewModel() {

    val detailMovieEvent = MutableLiveData<Event<ResultMovie>>()

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

    override fun onCleared() {
        super.onCleared()
        getDetailMovieUseCase.dispose()
    }
}