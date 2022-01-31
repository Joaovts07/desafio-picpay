package com.picpay.desafio.android.domain.usecase.di

import com.picpay.desafio.android.domain.usecase.GetUserUseCase
import com.picpay.desafio.android.domain.usecase.GetUserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DomainModule {
    @Binds
    fun bindGetUsersUseCase(useCase: GetUserUseCaseImpl): GetUserUseCase
}