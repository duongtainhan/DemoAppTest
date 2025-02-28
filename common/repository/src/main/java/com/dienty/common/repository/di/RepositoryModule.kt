package com.dienty.common.repository.di

import android.annotation.SuppressLint
import com.dienty.common.local.dao.RemoteKeysDao
import com.dienty.common.local.dao.UserDao
import com.dienty.common.local.db.AppDatabase
import com.dienty.common.remote.service.GithubService
import com.dienty.common.repository.detail.DetailRepository
import com.dienty.common.repository.home.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@SuppressLint("VisibleForTests")
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideHomeRepository(
        service: GithubService,
        appAppDatabase: AppDatabase,
        userDao: UserDao,
        remoteKeysDao: RemoteKeysDao
    ) = HomeRepository(service, appAppDatabase, userDao, remoteKeysDao)

    @Singleton
    @Provides
    fun provideDetailRepository(service: GithubService, dispatcher: CoroutineDispatcher) = DetailRepository(service, dispatcher)
}