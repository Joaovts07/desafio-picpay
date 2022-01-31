package com.picpay.desafio.android.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.picpay.desafio.android.data.db.entity.UsersEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {
    @Insert(onConflict = REPLACE)
    suspend fun insert(users:UsersEntity) : Long

    @Update
    suspend fun update(users: UsersEntity)

    @Query("DELETE FROM users")
    suspend fun deleteAll()

    @Query("SELECT * FROM users")
    fun getAll() : Flow<List<UsersEntity>>
}