package com.redveloper.movies.domain.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.redveloper.movies.TestRxSchedulers
import com.redveloper.movies.alwaysAllowExecute
import com.redveloper.movies.domain.entity.Genre
import com.redveloper.movies.domain.repository.MovieRepository
import com.redveloper.movies.domain.usecase.base.BaseUseCaseImpl
import io.reactivex.Single
import net.bytebuddy.utility.RandomString
import org.junit.Before
import org.junit.Test
import kotlin.random.Random
import kotlin.test.assertEquals

class GetGenreMovieUseCaseTest {
    lateinit var baseUseCaseImpl: BaseUseCaseImpl
    lateinit var movieRepository: MovieRepository
    lateinit var useCase: GetGenreMovieUseCase

    @Before
    fun before(){
        baseUseCaseImpl = mock()
        baseUseCaseImpl.alwaysAllowExecute()
        movieRepository = mock()
        useCase = GetGenreMovieUseCase(
            baseUseCaseImpl, movieRepository, TestRxSchedulers()
        )
    }

    @Test
    fun successExecute(){
        val genres = listOf(
            Genre(id = Random.nextInt(1, 10), name = RandomString.make()),
            Genre(id = Random.nextInt(1, 10), name = RandomString.make()),
            Genre(id = Random.nextInt(1, 10), name = RandomString.make()),
        )

        lateinit var result: List<Genre>

        whenever(movieRepository.getGenreMovie()).thenReturn(Single.just(genres))

        useCase.execute(
            GetGenreMovieUseCase.Output(
                success = {
                    result = it
                }
            )
        )

        assertEquals(
            expected = genres,
            actual = result
        )
    }

    @Test
    fun errorExecute(){
        val errorMessage = "you got error"

        lateinit var result: String

        whenever(movieRepository.getGenreMovie()).thenReturn(Single.error(Exception(errorMessage)))

        useCase.execute(
            GetGenreMovieUseCase.Output(
                error = {
                    result = it
                }
            )
        )

        assertEquals(
            expected = errorMessage,
            actual = result
        )
    }
}