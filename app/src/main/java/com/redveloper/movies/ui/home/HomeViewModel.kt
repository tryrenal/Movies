package com.redveloper.movies.ui.home

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

    init {
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

    fun getMovies(genreId: Int){
        setLoading(true)
        getMoviesUseCase.execute(genreId = genreId)
    }

    fun loadMoreMovies(){
        setLoading(true)
        getMoviesUseCase.loadMore()
    }

    override fun onCleared() {
        super.onCleared()
        getMoviesUseCase.clear()
    }
}