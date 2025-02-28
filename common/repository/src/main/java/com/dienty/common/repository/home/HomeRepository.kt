package com.dienty.common.repository.home

import androidx.annotation.VisibleForTesting
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dienty.common.local.dao.RemoteKeysDao
import com.dienty.common.local.dao.UserDao
import com.dienty.common.local.db.AppDatabase
import com.dienty.common.model.entity.UserEntity
import com.dienty.common.remote.service.GithubService
import com.dienty.common.repository.mapper.UserRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepository @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal val githubService: GithubService,
    @get:VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal val appDatabase: AppDatabase,
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val userDao: UserDao,
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val remoteKeysDao: RemoteKeysDao
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getUsers(): Flow<PagingData<UserEntity>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 10,
            initialLoadSize = 20, // How many items you want to load initially
        ),
        pagingSourceFactory = {
            // The pagingSourceFactory lambda should always return a brand new PagingSource
            // when invoked as PagingSource instances are not reusable.
            userDao.getUsers()
        },
        remoteMediator = UserRemoteMediator(
            githubService,
            appDatabase,
            userDao,
            remoteKeysDao
        )
    ).flow
}