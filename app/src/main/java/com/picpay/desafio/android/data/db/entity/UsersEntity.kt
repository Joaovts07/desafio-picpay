package com.picpay.desafio.android.data.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.picpay.desafio.android.domain.model.User
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "users")
data class  UsersEntity(
    @PrimaryKey
    val id: Long = 0,
    val name: String,
    val img: String,
    val username: String

) : Parcelable

fun UsersEntity.toUser() = User(
    id = this.id.toInt(),
    name = this.name,
    img = this.img,
    username = this.username

)