package com.picpay.desafio.android.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.picpay.desafio.android.data.db.entity.UsersEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String
) : Parcelable


fun User.toUserEntity() = UsersEntity(
    id = this.id.toLong(),
    name = this.name,
    img = this.img,
    username = this.username

)