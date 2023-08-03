package com.redveloper.movies.database.detail

import com.redveloper.movies.domain.entity.DetailMovie
import com.redveloper.movies.domain.repository.database.DetailMovieDatabase
import com.redveloper.movies.utils.convertDateStringToDate
import com.redveloper.movies.utils.convertDateToStringDate
import io.reactivex.Completable
import io.reactivex.Single
import java.util.Date
import javax.inject.Inject

class DetailMovieDatabaseImpl @Inject constructor(
    private val detailMovieDao: DetailMovieDao
) : DetailMovieDatabase{

    override fun insertDetailMovie(data: DetailMovie): Completable {
        val detailEntity = DetailMovieEntity(
            id = data.id,
            youtubeKey = data.youtubeKey,
            backdropPath = data.backdropPath,
            originalTitle = data.originalTitle,
            overview = data.overview,
            posterPath = data.posterPath,
            releaseDate = convertDateToStringDate(data.releaseDate ?: Date()),
            status = data.status,
            tagline = data.tagline
        )
        return detailMovieDao.insertDetailMovie(detailEntity)
    }

    override fun getDetailMovie(movieId: Int): Single<DetailMovie> {
        return detailMovieDao.getDetailMovieById(
            movieId = movieId
        ).map {
            DetailMovie(
                id = it.id,
                youtubeKey = it.youtubeKey,
                backdropPath = it.backdropPath,
                originalTitle = it.originalTitle,
                overview = it.overview,
                posterPath = it.posterPath,
                releaseDate = convertDateStringToDate(it.releaseDate) ?: Date(),
                status = it.status,
                tagline = it.tagline
            )
        }
    }
}