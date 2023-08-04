package com.redveloper.movies

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.whenever
import com.redveloper.movies.domain.usecase.base.IBaseUseCase

fun IBaseUseCase.alwaysAllowExecute(){
    argumentCaptor<IBaseUseCase.AllowExecuteCallback> {
        whenever(allowExecute(capture())).then{
            firstValue.allow()
        }
    }
}