package com.dienty.common.model.response

import com.dienty.common.model.entity.UserEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(

    val login: String?,

    @Json(name = "avatar_url")
    val avatarUrl: String?,

    @Json(name = "html_url")
    val htmlUrl: String?
)

fun UserResponse?.toUserEntity() = this?.run {
    UserEntity(
        username = login.orEmpty(),
        avatarUrl = avatarUrl.orEmpty(),
        htmlUrl = htmlUrl.orEmpty(),
        page = 0
    )
} ?: UserEntity("", "", "", 0)

fun List<UserResponse?>?.toUserEntities() = this?.mapNotNull { it.toUserEntity() }.orEmpty()