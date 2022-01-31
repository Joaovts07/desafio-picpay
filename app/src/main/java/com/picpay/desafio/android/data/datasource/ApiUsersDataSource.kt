package com.picpay.desafio.android.data.datasource

import androidx.room.withTransaction
import com.picpay.desafio.android.PicPayService
import com.picpay.desafio.android.data.NullUsersException
import com.picpay.desafio.android.data.db.AppDataBase
import com.picpay.desafio.android.data.db.entity.toUser
import com.picpay.desafio.android.data.util.Resource
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.model.toUserEntity
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class ApiUsersDataSource @Inject constructor(
    private val picPayService: PicPayService,
    private val appDataBase: AppDataBase
) : UsersDataSource {

    override fun getUsers(): Flow<List<User>> {

        val response = appDataBase.usersDao().getAll()

        return try {
            networkBoundResource(
                query = {
                    response
                },
                fetch = {
                    picPayService.getUsers()
                },
                saveFetchResult = { users ->
                    appDataBase.withTransaction{
                        users.map {
                            appDataBase.usersDao().insert(it.toUserEntity() ?: throw NullUsersException)
                        }
                    }
                }
            ).map {
                it.data?.map { userDTO ->
                    userDTO.toUser()
                } ?: throw Exception("aqui")
            }
        } catch (e:Exception){
            throw Exception(e.message)
        }
    }

}
inline fun <T, R> networkBoundResource(
    crossinline fetch: suspend () -> R,
    crossinline saveFetchResult: suspend (R) -> Unit,
    crossinline query: () -> Flow<T>,
    crossinline shouldFetch: (T) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))
        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}



