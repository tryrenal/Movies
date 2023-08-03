package com.redveloper.movies.domain.usecase.base

import io.reactivex.disposables.Disposable

abstract class BaseUseCase (private val baseUseaCase: IBaseUseaCase){

    protected var isExecuting = false

    protected fun allowExecute(
        allow: () -> Unit,
        notInternet: () -> Unit
    ){
        baseUseaCase.allowExecute(object : IBaseUseaCase.AllowExecuteCallback{
            override fun allow() {
                allow.invoke()
            }

            override fun notInternet() {
                notInternet.invoke()
            }
        })
    }

    fun addDisposable(disposable: Disposable){
        baseUseaCase.addDisposable(disposable)
    }

    open fun clear(){
        baseUseaCase.clear()
    }
}