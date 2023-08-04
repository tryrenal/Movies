package com.redveloper.movies.di

import android.content.Context
import com.redveloper.movies.MyApp
import com.redveloper.movies.domain.usecase.base.BaseUseCaseImpl
import com.redveloper.movies.domain.usecase.base.IBaseUseCase
import com.redveloper.movies.utils.RxSchedulers
import com.redveloper.movies.utils.RxSchedulersImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    fun provideAppContext(myApp: MyApp) : Context = myApp.applicationContext

    @Provides
    @Singleton
    fun provideScheduler(): RxSchedulers = RxSchedulersImpl()

    @Provides
    fun provideBaseUseCase(
        context: Context,
        schedulers: RxSchedulers,
    ): IBaseUseCase {
        return BaseUseCaseImpl(schedulers, context)
    }
}