package com.picpay.desafio.android.data.datasource

import com.picpay.desafio.android.PicPayService
import com.picpay.desafio.android.data.NullUsersException
import com.picpay.desafio.android.data.db.dao.UsersDao
import com.picpay.desafio.android.data.db.entity.toUser
import com.picpay.desafio.android.data.util.Resource
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.model.toUserEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ApiUsersDataSource @Inject constructor(
    private val picPayService: PicPayService,
    private val usersDao: UsersDao
) : UsersDataSource {

    override fun getUsers(): Flow<List<User>> {

        return networkBoundResource(
                query = {
                    usersDao.getAll()
                },
                fetch = {
                    picPayService.getUsers()
                },
                saveFetchResult = { users ->
                    users.map {
                        usersDao.insert(it.toUserEntity() ?: throw NullUsersException)
                    }
                }
        ).map {
            it.data?.map { userDTO ->
                userDTO.toUser()
            } ?: throw NullUsersException
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



