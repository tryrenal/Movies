package com.redveloper.movies.di

import com.redveloper.movies.api.MoviesApiService
import com.redveloper.movies.api.Test.TestApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class RetrofitApiServiceModule {

    @Provides
    @Singleton
    fun provideMovieApiService(retrofit: Retrofit): MoviesApiService{
        return retrofit.create(MoviesApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTestApiService(@Named("TestRetrofit") retrofit: Retrofit): TestApiService{
        return retrofit.create(TestApiService::class.java)
    }
}