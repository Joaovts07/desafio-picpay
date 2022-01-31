package com.picpay.desafio.android.data.di

import com.picpay.desafio.android.data.datasource.ApiUsersDataSource
import com.picpay.desafio.android.data.datasource.UsersDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Singleton
    @Binds
    fun bindsUsersDataSource(dataSource: ApiUsersDataSource) : UsersDataSource
}