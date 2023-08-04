package com.redveloper.movies.di

import android.content.Context
import androidx.room.Room
import com.redveloper.movies.database.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DaoModule::class])
class DatabaseModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(context: Context): MovieDatabase{
        return Room.databaseBuilder(
            context, MovieDatabase::class.java, "Movie-DB"
        ).build()
    }
}