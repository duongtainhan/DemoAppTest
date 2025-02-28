package com.dienty.common.model.entity

import androidx.room.Entity

@Entity(tableName = "detail_entity")
data class DetailEntity (

    val username: String,

    val avatarUrl: String,

    val htmlUrl: String,

    val location: String,

    val followers: Int,

    val followings: Int
)