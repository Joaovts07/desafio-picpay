package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.data.repository.UsersRepository
import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetUserUseCaseImpl @Inject constructor(
    private val usersRepository: UsersRepository
) : GetUserUseCase{
    override fun invoke(): Flow<List<User>> = usersRepository.getUsers()

}