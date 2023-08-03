package com.redveloper.movies.domain.repository

import com.redveloper.movies.domain.entity.DetailMovie
import com.redveloper.movies.domain.entity.Movies
import com.redveloper.movies.domain.entity.ResultMovie
import com.redveloper.movies.domain.entity.Reviews
import com.redveloper.movies.domain.entity.Trailer
import com.redveloper.movies.domain.repository.api.MoviesApi
import com.redveloper.movies.domain.repository.database.DetailMovieDatabase
import com.redveloper.movies.utils.RxSchedulers
import com.redveloper.movies.utils.convertDateStringToDate
import io.reactivex.Single
import java.util.Date
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val moviesApi: MoviesApi,
    private val detailMovieDatabase: DetailMovieDatabase,
    private val schedulers: RxSchedulers
) {

    fun getMovies(page: Int): Single<Movies>{
        return moviesApi.getMovies(page)
            .subscribeOn(schedulers.network())
    }

    fun getDetailMovie(id: Int): Single<DetailMovie>{
        return detailMovieDatabase.getDetailMovie(id)
            .subscribeOn(schedulers.database())
            .flatMap<DetailMovie?> { data ->
                if (data != null){
                    Single.just(data)
                } else {
                    Single.error(Exception("detail movie null"))
                }
            }
            .onErrorResumeNext(
                fetchDetailMovieDataFromApi(id = id)
                    .doOnSuccess { data ->
                        detailMovieDatabase.insertDetailMovie(data)
                            .subscribeOn(schedulers.database())
                            .subscribe()
                    }.subscribeOn(schedulers.network())
            )
    }

    private fun fetchDetailMovieDataFromApi(id: Int): Single<DetailMovie> {
        return Single.zip(
            moviesApi.getDetailMovie(id),
            moviesApi.getTrailer(id)
        ) { detail, trailer ->
            DetailMovie(
                id = detail.id,
                youtubeKey = trailer.key ?: "",
                backdropPath = detail.backdropPath ?: "",
                originalTitle = detail.originalTitle ?: "",
                overview = detail.overview ?: "",
                posterPath = detail.posterPath ?: "",
                releaseDate = if (!detail.releaseDate.isNullOrBlank())
                    convertDateStringToDate(detail.releaseDate) ?: Date() else Date(),
                status = detail.status ?: "",
                tagline = detail.tagline ?: ""
            )
        }
    }


    fun getReviewMovie(id: Int): Single<Reviews>{
        return moviesApi.getReviewMovie(id)
            .subscribeOn(schedulers.network())
    }
}