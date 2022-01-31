package com.picpay.desafio.android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.db.dao.UsersDao
import com.picpay.desafio.android.data.db.entity.UsersEntity

@Database(entities = [UsersEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun usersDao() : UsersDao

}