package com.redveloper.movies.di

import com.redveloper.movies.database.MovieDatabase
import com.redveloper.movies.database.detail.DetailMovieDao
import com.redveloper.movies.database.detail.DetailMovieDatabaseImpl
import com.redveloper.movies.domain.repository.database.DetailMovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DaoModule {

    @Provides
    @Singleton
    fun provideDetailMovieDao(db: MovieDatabase): DetailMovieDao{
        return db.detailMovieDao()
    }

    @Provides
    @Singleton
    fun bindDetailMovieDatabase(dao: DetailMovieDao): DetailMovieDatabase {
        return DetailMovieDatabaseImpl(dao)
    }
}