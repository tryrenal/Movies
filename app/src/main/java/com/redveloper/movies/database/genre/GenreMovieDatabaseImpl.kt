package com.redveloper.movies.database.genre

import com.redveloper.movies.domain.entity.Genre
import com.redveloper.movies.domain.repository.database.GenreDatabase
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class GenreMovieDatabaseImpl @Inject constructor(
    private val genreMovieDao: GenreMovieDao
): GenreDatabase {
    override fun insertGenres(data: List<Genre>): Completable {
        val genreEntity = data.map {
            GenreMovieEntity(id = it.id, name = it.name)
        }

        return genreMovieDao
            .insertGenres(genreEntity)
    }

    override fun getGenres(): Single<List<Genre>> {
        return genreMovieDao.getGenres()
            .map {
                it.map { data ->
                    Genre(id = data.id, name = data.name)
                }
            }
    }
}