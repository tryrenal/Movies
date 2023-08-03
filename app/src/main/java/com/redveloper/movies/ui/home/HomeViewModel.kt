package com.redveloper.movies.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redveloper.movies.domain.entity.ResultMovie
import com.redveloper.movies.domain.usecase.GetMoviesUseCase
import com.redveloper.movies.utils.Event
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
): ViewModel() {

    val moviesEvent = MutableLiveData<Event<List<ResultMovie>>>()
    val showErrorEvent = MutableLiveData<Event<String>>()
    val loadingEvent = MutableLiveData<Event<Boolean>>()

    private fun setLoading(show: Boolean){
        loadingEvent.value = Event(show)
    }

    fun getMovies(){
        setLoading(true)
        getMoviesUseCase.execute()
        getMoviesUseCase.output = GetMoviesUseCase.Output(
            success = {
                setLoading(false)
                Log.i("dataMovies", it.toString())
                moviesEvent.value = Event(it)
            },
            error = {
                setLoading(false)
                Log.i("dataMovies", it)
                showErrorEvent.value = Event(it)
            }
        )
    }

    fun loadMoreMovies(){
        setLoading(true)
        getMoviesUseCase.loadMore()
        getMoviesUseCase.output = GetMoviesUseCase.Output(
            success = {
                setLoading(false)
                moviesEvent.value = Event(it)
            },
            error = {
                setLoading(false)
                showErrorEvent.value = Event(it)
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        getMoviesUseCase.dispose()
    }
}