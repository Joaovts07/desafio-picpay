package com.picpay.desafio.android.data.di

import android.content.Context
import androidx.room.Room
import com.picpay.desafio.android.data.db.AppDataBase
import com.picpay.desafio.android.data.db.dao.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDataBase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDataBase::class.java,
        "app_database"
    ).build()

    @Singleton
    @Provides
    fun provideUsersDao(dataBase: AppDataBase) : UsersDao{
      return dataBase.usersDao()
    }
}