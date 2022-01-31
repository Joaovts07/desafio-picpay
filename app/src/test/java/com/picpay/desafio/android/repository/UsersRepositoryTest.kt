package com.picpay.desafio.android.repository

import com.picpay.desafio.android.data.datasource.ApiUsersDataSource
import com.picpay.desafio.android.data.repository.UsersRepository
import com.picpay.desafio.android.domain.model.User
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UsersRepositoryTest {

    @MockK
    lateinit var dataSource: ApiUsersDataSource

    private val repository: UsersRepository by lazy {
        UsersRepository(dataSource)
    }

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `Should return success when call get users`() = runBlocking {
        val flow = flow {
            emit(mockResponse())
        }
        every { dataSource.getUsers() } answers { flow }
        val result = repository.getUsers()
        assertEquals(result.first()[0].id, 42)
    }
    private fun mockResponse() = listOf(
        User("","name",42,"username")
    )

}