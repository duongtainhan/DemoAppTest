package com.dienty.common.local.mockdata

import com.dienty.common.model.entity.RemoteKeys
import com.dienty.common.model.entity.UserEntity

object LocalMockData {

    fun getUsers(): List<UserEntity> {
        return listOf(
            UserEntity(
                username = "mojombo",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                htmlUrl = "https://github.com/mojombo",
                page = 1
            ),
            UserEntity(
                username = "defunkt",
                avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4",
                htmlUrl = "https://github.com/defunkt",
                page = 1
            ),
            UserEntity(
                username = "pjhyett",
                avatarUrl = "https://avatars.githubusercontent.com/u/3?v=4",
                htmlUrl = "https://github.com/pjhyett",
                page = 1
            ),
            UserEntity(
                username = "wycats",
                avatarUrl = "https://avatars.githubusercontent.com/u/4?v=4",
                htmlUrl = "https://github.com/wycats",
                page = 1
            ),
            UserEntity(
                username = "ezmobius",
                avatarUrl = "https://avatars.githubusercontent.com/u/5?v=4",
                htmlUrl = "https://github.com/ezmobius",
                page = 1
            ),
            UserEntity(
                username = "ivey",
                avatarUrl = "https://avatars.githubusercontent.com/u/6?v=4",
                htmlUrl = "https://github.com/ivey",
                page = 1
            ),
            UserEntity(
                username = "evanphx",
                avatarUrl = "https://avatars.githubusercontent.com/u/7?v=4",
                htmlUrl = "https://github.com/evanphx",
                page = 1
            )
        )
    }

    fun getRemoteKeys(): List<RemoteKeys> {
        return listOf(
            RemoteKeys(
                username = "mojombo",
                prevKey = 0,
                nextKey = 1,
                currentPage = 1,
                createdAt = 123L
            ),
            RemoteKeys(
                username = "defunkt",
                prevKey = 1,
                nextKey = 2,
                currentPage = 1,
                createdAt = 123L
            ),
            RemoteKeys(
                username = "pjhyett",
                prevKey = 2,
                nextKey = 3,
                currentPage = 1,
                createdAt = 123L
            ),
            RemoteKeys(
                username = "wycats",
                prevKey = 3,
                nextKey = 4,
                currentPage = 1,
                createdAt = 123L
            )
        )
    }
}