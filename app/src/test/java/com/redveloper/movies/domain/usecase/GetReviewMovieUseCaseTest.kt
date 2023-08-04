package com.redveloper.movies.domain.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.redveloper.movies.TestRxSchedulers
import com.redveloper.movies.alwaysAllowExecute
import com.redveloper.movies.domain.entity.ResultReview
import com.redveloper.movies.domain.entity.Reviews
import com.redveloper.movies.domain.repository.MovieRepository
import com.redveloper.movies.domain.usecase.base.BaseUseCaseImpl
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import java.util.Date
import kotlin.test.assertEquals

class GetReviewMovieUseCaseTest {
    lateinit var baseUseCaseImpl: BaseUseCaseImpl
    lateinit var movieRepository: MovieRepository
    lateinit var useCase: GetReviewMovieUseCase

    @Before
    fun before(){
        baseUseCaseImpl = mock()
        baseUseCaseImpl.alwaysAllowExecute()
        movieRepository = mock()
        useCase = GetReviewMovieUseCase(
            baseUseCaseImpl, movieRepository, TestRxSchedulers()
        )
    }

    @Test
    fun successExecute(){
        val movieId = 1
        val reviews = listOf(
            ResultReview(
                author = "author",
                content = "content",
                createdAt = Date(),
                updateAt = Date(),
                id = "123",
                url = "url"
            )
        )
        val review = Reviews(
            id = movieId,
            page = 1,
            totalPages = 2,
            totalResults = 10,
            result = reviews
        )

        lateinit var result: List<ResultReview>

        whenever(movieRepository.getReviewMovie(movieId)).thenReturn(Single.just(review))

        useCase.execute(
            GetReviewMovieUseCase.Input(movieId),
            GetReviewMovieUseCase.Output(
                success = {
                    result = it
                }
            )
        )

        assertEquals(reviews, result)
    }

    @Test
    fun errorExecute(){
        val errorMessage = "error message get review"
        val movieId = 1

        lateinit var result: String

        whenever(movieRepository.getReviewMovie(movieId)).thenReturn(Single.error(Exception(errorMessage)))

        useCase.execute(
            GetReviewMovieUseCase.Input(movieId),
            GetReviewMovieUseCase.Output(
                error = {
                    result = it
                }
            )
        )

        assertEquals(errorMessage, result)
    }
}