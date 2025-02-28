package com.dienty.common.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_entity")
data class UserEntity(

    @PrimaryKey
    @ColumnInfo(name = "username") val username: String,

    @ColumnInfo(name = "avatar_url") val avatarUrl: String,

    @ColumnInfo(name = "html_url") val htmlUrl: String,

    @ColumnInfo(name = "page") var page: Int
)
