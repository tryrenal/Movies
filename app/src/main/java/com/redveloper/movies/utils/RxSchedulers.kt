package com.redveloper.movies.utils

import io.reactivex.Scheduler

interface RxSchedulers {
    fun ui(): Scheduler
    fun database(): Scheduler
    fun network(): Scheduler
}