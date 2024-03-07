package com.redveloper.movies.ui.test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redveloper.movies.domain.entity.TestModel
import com.redveloper.movies.domain.usecase.GetTestListUseCase
import com.redveloper.movies.utils.Event
import javax.inject.Inject

class TestViewModel @Inject constructor(
    private val testUseCase: GetTestListUseCase
): ViewModel() {

    fun getData() {
        testUseCase.execute()
        testUseCase.output = GetTestListUseCase.Output(
            success = {
                testDataEvent.value = Event(it)
            },
            error = {
                showErrorEvent.value = Event(it)
            }
        )
    }

    val testDataEvent = MutableLiveData<Event<List<TestModel>>>()
    val showErrorEvent = MutableLiveData<Event<String>>()
}