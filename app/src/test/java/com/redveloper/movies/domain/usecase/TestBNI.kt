package com.redveloper.movies.domain.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

interface LoginRepository{
    fun Login(email: String, password: String): Result<Boolean>
}

class TestBNI {

    lateinit var loginRepository: LoginRepository
    lateinit var testBNI : TestBNI

    @Before
    fun setup(){
        loginRepository = mock()
        testBNI = TestBNI(loginRepository)
    }

    @Test
    fun successLogin(){
        val email = "renal"
        val password = "renal"

        val result = loginRepository.Login(email, password).getOrNull()

        val expected = true
        assertEquals(result, expected)
    }

    @Test
    fun failedBecauseEmailBlankLogin(){
        val email = ""
        val password = "renal"

        val result = loginRepository.Login(email, password).getOrNull()

        val expected = false
        assertEquals(result, expected)
    }

    @Test
    fun failedBecausePasswordBlankLogin(){
        val email = "email"
        val password = ""

        val result = loginRepository.Login(email, password).getOrNull()

        val expected = false
        assertEquals(result, expected)
    }

    @Test
    fun failedBecauseEmailPasswordBlankLogin(){
        val email = ""
        val password = ""

        val result = loginRepository.Login(email, password).getOrNull()

        val expected = false
        assertEquals(result, expected)
    }


}