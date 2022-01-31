package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.datasource.UsersDataSource
import com.picpay.desafio.android.data.db.dao.UsersDao
import com.picpay.desafio.android.data.db.entity.UsersEntity
import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val dataSource: UsersDataSource
) {

    fun getUsers(): Flow<List<User>> = dataSource.getUsers()
}