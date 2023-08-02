package com.redveloper.movies.di

import com.redveloper.movies.api.MoviesApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RetrofitApiServiceModule {

    @Provides
    @Singleton
    fun provideMovieApiService(retrofit: Retrofit): MoviesApiService{
        return retrofit.create(MoviesApiService::class.java)
    }
}