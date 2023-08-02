package com.redveloper.movies.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors
import javax.inject.Singleton

@Singleton
class RxSchedulersImpl : RxSchedulers {
    private val databaseExecutor = Executors.newSingleThreadExecutor()

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun database(): Scheduler {
        return Schedulers.from(databaseExecutor)
    }

    override fun network(): Scheduler {
        return Schedulers.io()
    }
}