package com.redveloper.movies.di

import com.redveloper.movies.api.MoviesApiImpl
import com.redveloper.movies.api.Test.TestApiImpl
import com.redveloper.movies.domain.repository.api.MoviesApi
import com.redveloper.movies.domain.repository.api.TestApi
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ApiModule {
    @Binds
    @Singleton
    abstract fun bindMovieApi(api: MoviesApiImpl): MoviesApi

    @Binds
    @Singleton
    abstract fun bindTestApi(api: TestApiImpl): TestApi
}