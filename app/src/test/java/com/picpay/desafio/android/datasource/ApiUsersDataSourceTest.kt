package com.picpay.desafio.android.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.picpay.desafio.android.PicPayService
import com.picpay.desafio.android.data.datasource.ApiUsersDataSource
import com.picpay.desafio.android.data.db.dao.UsersDao
import com.picpay.desafio.android.data.db.entity.UsersEntity
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.util.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ApiUsersDataSourceTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @MockK
    lateinit var service: PicPayService

    @MockK
    lateinit var usersDao: UsersDao

    private lateinit var datasource: ApiUsersDataSource
    companion object{
        val userEntity = mutableListOf(UsersEntity(0, "name", "", "default_user"))
        val users = listOf(User("", "name", 42, "username"),
            User("","name2",2,"username2"))
    }

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        datasource = ApiUsersDataSource(service,usersDao)

    }

    @Test
    fun `datasource should emit a users list`(){
        runTest {
            coEvery {
                usersDao.getAll()
            } returns flow {
                emit(userEntity)
            }

            datasource.getUsers().collect{
                assert(it.isNotEmpty())
            }
        }
    }
    @Test
    fun `should call database dao functions`(){
        runTest {
            coEvery {
                usersDao.getAll()
            } returns flow {
                emit(userEntity)
            }

            coEvery {
                usersDao.insert(any())
            } coAnswers   {
                any()
            }
            coEvery {
                service.getUsers()
            } returns users
            datasource.getUsers().collect {

            }
            coVerify {
                usersDao.getAll()
                usersDao.insert(any())
            }
        }
    }

}
