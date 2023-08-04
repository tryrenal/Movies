package com.redveloper.movies.ui.genre

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redveloper.movies.domain.entity.Genre
import com.redveloper.movies.domain.usecase.GetGenreMovieUseCase
import com.redveloper.movies.utils.Event
import javax.inject.Inject

class GenreViewModel @Inject constructor(
    private val getGenreMovieUseCase: GetGenreMovieUseCase
): ViewModel() {

    val genresEvent = MutableLiveData<Event<List<Genre>>>()
    val errorEvent = MutableLiveData<Event<String>>()
    val loadingEvent = MutableLiveData<Event<Boolean>>()

    private fun setLoading(show: Boolean){
        loadingEvent.value = Event(show)
    }

    fun getGenreMovie(){
        setLoading(true)
        getGenreMovieUseCase.execute(
            GetGenreMovieUseCase.Output(
                success = {
                    setLoading(false)
                    genresEvent.value = Event(it)
                },
                error = {
                    setLoading(false)
                    errorEvent.value = Event(it)
                }
            )
        )
    }

    override fun onCleared() {
        super.onCleared()
        getGenreMovieUseCase.clear()
    }
}