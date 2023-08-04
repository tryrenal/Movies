package com.redveloper.movies.domain.usecase

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.clearInvocations
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.redveloper.movies.TestRxSchedulers
import com.redveloper.movies.alwaysAllowExecute
import com.redveloper.movies.domain.entity.Movies
import com.redveloper.movies.domain.entity.ResultMovie
import com.redveloper.movies.domain.repository.MovieRepository
import com.redveloper.movies.domain.usecase.base.BaseUseCaseImpl
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class GetMoviesUseCaseTest {
    lateinit var baseUseCaseImpl: BaseUseCaseImpl
    lateinit var movieRepository: MovieRepository
    lateinit var useCase: GetMoviesUseCase

    @Before
    fun before(){
        baseUseCaseImpl = mock()
        baseUseCaseImpl.alwaysAllowExecute()
        movieRepository = mock()
        useCase = GetMoviesUseCase(
            baseUseCaseImpl, movieRepository, TestRxSchedulers()
        )
    }

    @Test
    fun successExecute(){
        val page = 1
        val genreId = 28
        val listMovie = listOf(
            ResultMovie(
                adult = false,
                backdropPath = "/backdropdpath",
                id = 0,
                originalLanguange = "in",
                originalTitle = "title movie",
                overview = "overview",
                popularity = 92.9,
                posterPath = "/posterpath",
                releaseDate = "22/333/44",
                title = "title novie",
                video = true,
                voteAverage = 89.2,
                voteCount = 12313,
                budget = 12312312,
                homepage = "homepage",
                imdbId = "8/9",
                revenue = 123,
                runtime = 123,
                status = "status",
                tagline = "tagline"
            )
        )
        val movies = Movies(
            page = page,
            totalPages = 2,
            totalResults = 10,
            reslutMovie = listMovie
        )

        val usecaseOutput : GetMoviesUseCase.Output = mock()

        //given
        whenever(movieRepository.getMovies(page, genreId)).thenReturn(Single.just(movies.copy()))
        useCase.output = usecaseOutput

        //when
        useCase.execute(genreId)

        //then verify
        verify(usecaseOutput).success?.invoke(listMovie)
        verify(usecaseOutput, never()).error?.invoke(any())

        //then check value
        useCase.output = GetMoviesUseCase.Output(
            success = {
                assertEquals(listMovie, it)
            }
        )
    }

    @Test
    fun errorExecute(){
        val page = 1
        val genreId = 28

        val errorMessage = "error get data"

        val usecaseOutput : GetMoviesUseCase.Output = mock()

        //given
        whenever(movieRepository.getMovies(page, genreId)).thenReturn(Single.error(Exception(errorMessage)))
        useCase.output = usecaseOutput

        //when
        useCase.execute(genreId)

        //then verify
        verify(usecaseOutput, never()).success?.invoke(any())
        verify(usecaseOutput).error?.invoke(errorMessage)

        //then check value
        useCase.output = GetMoviesUseCase.Output(
            error = {
                assertEquals(errorMessage, it)
            }
        )
    }

    @Test
    fun successLoadMore(){
        val page = 1
        val nextPage = 2
        val genreId = 28

        val listMovie = listOf(
            ResultMovie(
                adult = false,
                backdropPath = "/backdropdpath",
                id = 0,
                originalLanguange = "in",
                originalTitle = "title movie",
                overview = "overview",
                popularity = 92.9,
                posterPath = "/posterpath",
                releaseDate = "22/333/44",
                title = "title novie",
                video = true,
                voteAverage = 89.2,
                voteCount = 12313,
                budget = 12312312,
                homepage = "homepage",
                imdbId = "8/9",
                revenue = 123,
                runtime = 123,
                status = "status",
                tagline = "tagline"
            )
        )
        val movies = Movies(
            page = page,
            totalPages = nextPage,
            totalResults = 10,
            reslutMovie = listMovie
        )

        val usecaseOutput : GetMoviesUseCase.Output = mock()

        //given
        whenever(movieRepository.getMovies(page,genreId)).thenReturn(Single.just(movies.copy()))
        useCase.output = usecaseOutput

        //given first execute
        useCase.execute(genreId)

        //when
        useCase.loadMore()

        //then check verify
        verify(usecaseOutput, times(2)).success?.invoke(listMovie)
        verify(usecaseOutput, never()).error?.invoke(any())

        //then check value
        useCase.output = GetMoviesUseCase.Output(
            success = {
                assertEquals(listMovie, it)
            }
        )
    }

    @Test
    fun errorLoadMore(){
        val page = 1
        val nextPage = 2
        val genreId = 28

        val listMovie = listOf(
            ResultMovie()
        )
        val movies = Movies(
            page = page,
            totalPages = nextPage,
            totalResults = 10,
            reslutMovie = listMovie
        )

        val errorMessage = "error load more"

        val usecaseOutput: GetMoviesUseCase.Output = mock()
        useCase.output = usecaseOutput
        //given first execute
        whenever(movieRepository.getMovies(page, genreId)).thenReturn(Single.just(movies))
        useCase.execute(genreId)

        //given
        clearInvocations(usecaseOutput)
        whenever(movieRepository.getMovies(page, genreId)).thenReturn(Single.error(Exception(errorMessage)))

        //when
        useCase.loadMore()

        //then verify
        verify(usecaseOutput).error?.invoke(errorMessage)

        //then check
        useCase.output = GetMoviesUseCase.Output(
            error = {
                assertEquals(errorMessage, it)
            }
        )
    }
}