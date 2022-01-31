package com.picpay.desafio.android.datasource

import com.nhaarman.mockitokotlin2.any
import com.picpay.desafio.android.PicPayService
import com.picpay.desafio.android.data.datasource.ApiUsersDataSource
import com.picpay.desafio.android.data.db.AppDataBase
import com.picpay.desafio.android.data.db.entity.UsersEntity
import com.picpay.desafio.android.domain.model.User
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ApiUsersDataSourceTest {

    @MockK
    lateinit var service: PicPayService

    @MockK
    lateinit var dataBase: AppDataBase

    lateinit var userEntity: List<UsersEntity>

    lateinit var user: User

    lateinit var datasource: ApiUsersDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        datasource = ApiUsersDataSource(service,dataBase)
        userEntity = listOf(UsersEntity(0, "name", "", "default_user"))
        user = User("", "name", 42, "username")

    }

    @Test
    fun `repository should persist remote data in db`(){
        runTest {
            coEvery {
                dataBase.usersDao().getAll()
            } returns flow {
                emit(userEntity)
            }

            coEvery {
                dataBase.usersDao().insert(userEntity.first())
            } coAnswers {
                any()
            }

            datasource.getUsers().collect{
                assert(it.isNotEmpty())
            }
        }
    }
}
