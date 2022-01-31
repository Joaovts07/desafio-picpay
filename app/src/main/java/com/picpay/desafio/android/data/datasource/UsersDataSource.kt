package com.picpay.desafio.android.data.datasource

import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UsersDataSource {

    fun getUsers(): Flow<List<User>>
}