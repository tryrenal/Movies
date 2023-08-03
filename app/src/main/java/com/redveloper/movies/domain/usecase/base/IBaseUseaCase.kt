package com.redveloper.movies.domain.usecase.base

import io.reactivex.disposables.Disposable

interface IBaseUseaCase {
    fun addDisposable(disposable: Disposable)

    fun clear()

    interface AllowExecuteCallback {
        fun allow()
        fun notInternet()
    }

    fun allowExecute(
        allow: AllowExecuteCallback
    )
}