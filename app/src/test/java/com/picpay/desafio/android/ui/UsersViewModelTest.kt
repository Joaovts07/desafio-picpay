package com.picpay.desafio.android.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.usecase.GetUserUseCaseImpl
import com.picpay.desafio.android.ui.users.UsersViewModel
import com.picpay.desafio.android.util.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UsersViewModelTest {

    lateinit var viewModel: UsersViewModel
    lateinit var users: List<User>

    @MockK
    private lateinit var  useCase: GetUserUseCaseImpl

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var usersList: List<User>

    @MockK
    private lateinit var mockObserver: Observer<UsersViewModel.UserState>

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        //Dispatchers.setMain(mainThreadSurrogate)
        viewModel = UsersViewModel(useCase)
        users = listOf(
            User("", "username_0", 0, "name"),
            User("", "username_0", 1, "name"),
            User("", "username_0", 2, ""),
            User("", "username_0", 3, ""),
        )
        usersList = listOf(
            User("", "username_0", 0, "name"),
            User("", "username_0", 1, "name"),
            User("", "username_0", 2, ""),
            User("", "username_0", 3, ""),
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
    }

    @Test
    fun `should ViewModel call use case`(){

        runBlocking {
            val flow = flow {
                emit(usersList)
            }
            coEvery { useCase() } returns flow
            every { mockObserver.onChanged(any()) } answers {}
            viewModel.usersData.observeForever(mockObserver)
            viewModel.getUsers()
            coVerify  {
                useCase.invoke()
                mockObserver.onChanged(UsersViewModel.UserState.LOADING)
                mockObserver.onChanged(UsersViewModel.UserState.SUCCESS(usersList))
            }

        }
    }

    @Test
    fun `viewModel should update viewState to SuccessState`(){
        runBlocking {
            val flow = flow {
                emit(usersList)
            }
            coEvery { useCase() } returns flow
            viewModel.getUsers()
            coVerify  { useCase.invoke() }

        }
    }
}