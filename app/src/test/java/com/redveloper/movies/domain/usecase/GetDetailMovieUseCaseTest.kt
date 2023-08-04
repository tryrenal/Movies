package com.redveloper.movies.domain.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.redveloper.movies.TestRxSchedulers
import com.redveloper.movies.alwaysAllowExecute
import com.redveloper.movies.domain.entity.DetailMovie
import com.redveloper.movies.domain.repository.MovieRepository
import com.redveloper.movies.domain.usecase.base.BaseUseCaseImpl
import com.redveloper.movies.utils.RxSchedulers
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import java.util.Date
import kotlin.test.assertEquals

class GetDetailMovieUseCaseTest {
    lateinit var baseUseCase: BaseUseCaseImpl
    lateinit var movieRepository: MovieRepository
    lateinit var useCase: GetDetailMovieUseCase

    @Before
    fun before(){
        baseUseCase = mock()
        baseUseCase.alwaysAllowExecute()
        movieRepository = mock()
        useCase = GetDetailMovieUseCase(
            baseUseCase, movieRepository, TestRxSchedulers()
        )
    }

    @Test
    fun successExecuteDetailMovie(){
        val page = 1
        val imageUrl = "/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg"
        val desc = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout."
        val detailMovie = DetailMovie(
            id = 0,
            youtubeKey = "youtubekey",
            backdropPath = imageUrl,
            originalTitle = "Original Title Movie",
            overview = desc,
            posterPath = imageUrl,
            releaseDate = Date(),
            status = "now playing",
            tagline = "It is good movie"
        )

        lateinit var result: DetailMovie

        //given
        whenever(movieRepository.getDetailMovie(page)).thenReturn(Single.just(detailMovie.copy()))

        //when
        useCase.execute(
            GetDetailMovieUseCase.Input(page),
            GetDetailMovieUseCase.Output(
                success = {
                    result = it
                }
            )
        )

        //then
        assertEquals(
            expected = detailMovie.youtubeKey,
            actual = result.youtubeKey
        )
    }

    @Test
    fun errorExecuteDetailMovie(){
        val page = 1
        val errorMessage = "you got error"

        lateinit var result: String

        //given
        whenever(movieRepository.getDetailMovie(page)).thenReturn(Single.error(Exception(errorMessage)))

        //when
        useCase.execute(
            GetDetailMovieUseCase.Input(page),
            GetDetailMovieUseCase.Output(
                error = {
                    result = it
                }
            )
        )

        //then
        assertEquals(
            expected = errorMessage,
            actual = result
        )
    }
}