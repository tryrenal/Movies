package com.redveloper.movies.domain.repository.api

import com.redveloper.movies.domain.entity.Genre
import com.redveloper.movies.domain.entity.Movies
import com.redveloper.movies.domain.entity.ResultMovie
import com.redveloper.movies.domain.entity.Reviews
import com.redveloper.movies.domain.entity.Trailer
import io.reactivex.Single

interface MoviesApi {
    fun getMovies(page: Int): Single<Movies>
    fun getDetailMovie(id: Int): Single<ResultMovie>
    fun getReviewMovie(id: Int): Single<Reviews>
    fun getTrailer(id: Int): Single<Trailer>
    fun getGenres(): Single<List<Genre>>
}