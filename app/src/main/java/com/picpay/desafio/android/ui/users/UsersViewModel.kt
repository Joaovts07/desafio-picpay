package com.picpay.desafio.android.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel () {

    private val _usersData = MutableLiveData<UserState>()
    val usersData: LiveData<UserState> = _usersData

    fun getUsers(){
        getUserUseCase().onStart {
            _usersData.value = UserState.LOADING
        }.onEach {
            _usersData.value = UserState.SUCCESS(it)
        }.catch {
            _usersData.value = UserState.ERROR
        }.launchIn(viewModelScope)
    }

    sealed class UserState{
        object LOADING: UserState()
        object ERROR: UserState()
        data class SUCCESS(val usersList: List<User>) : UserState()
    }

}