package com.picpay.desafio.android

import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    suspend fun getUsers(): List<User>
}
