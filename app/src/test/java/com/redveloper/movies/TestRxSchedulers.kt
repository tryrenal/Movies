package com.redveloper.movies

import com.redveloper.movies.utils.RxSchedulers
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestRxSchedulers : RxSchedulers {
    override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun database(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun network(): Scheduler {
        return Schedulers.trampoline()
    }
}