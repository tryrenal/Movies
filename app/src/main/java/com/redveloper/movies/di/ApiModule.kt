package com.redveloper.movies.di

import com.redveloper.movies.api.MoviesApiImpl
import com.redveloper.movies.domain.repository.api.MoviesApi
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ApiModule {
    @Binds
    @Singleton
    abstract fun bindMovieApi(api: MoviesApiImpl): MoviesApi
}