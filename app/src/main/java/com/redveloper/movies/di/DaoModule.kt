package com.redveloper.movies.di

import com.redveloper.movies.database.MovieDatabase
import com.redveloper.movies.database.detail.DetailMovieDao
import com.redveloper.movies.database.detail.DetailMovieDatabaseImpl
import com.redveloper.movies.database.genre.GenreMovieDao
import com.redveloper.movies.database.genre.GenreMovieDatabaseImpl
import com.redveloper.movies.domain.repository.database.DetailMovieDatabase
import com.redveloper.movies.domain.repository.database.GenreDatabase
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
    fun provideGenreMovieDao(db: MovieDatabase): GenreMovieDao{
        return db.genreMovieDao()
    }

    @Provides
    @Singleton
    fun bindDetailMovieDatabase(dao: DetailMovieDao): DetailMovieDatabase {
        return DetailMovieDatabaseImpl(dao)
    }

    @Provides
    @Singleton
    fun bindGenreMovieDatabase(dao: GenreMovieDao): GenreDatabase {
        return GenreMovieDatabaseImpl(dao)
    }
}