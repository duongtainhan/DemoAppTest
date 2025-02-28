package com.dienty.common.model.response

import com.dienty.common.model.entity.DetailEntity
import com.dienty.core.extension.orZero
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailResponse(

    val login: String?,

    @Json(name = "avatar_url")
    val avatarUrl: String?,

    @Json(name = "html_url")
    val htmlUrl: String?,

    val location: String?,

    val followers: Int?,

    val following: Int?
)

fun DetailResponse?.toDetailEntity() = this?.run {
    DetailEntity(
        username = login.orEmpty(),
        avatarUrl = avatarUrl.orEmpty(),
        htmlUrl = htmlUrl.orEmpty(),
        location = location.orEmpty(),
        followers = followers.orZero(),
        followings = following.orZero()
    )
} ?: DetailEntity("", "", "", "", 0, 0)