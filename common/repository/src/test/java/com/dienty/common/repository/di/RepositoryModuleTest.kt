package com.dienty.common.repository.di

import com.dienty.common.local.dao.RemoteKeysDao
import com.dienty.common.local.dao.UserDao
import com.dienty.common.local.db.AppDatabase
import com.dienty.common.remote.service.GithubService
import com.dienty.core.testing.MockkUnitTest
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import org.junit.Assert
import org.junit.Test

class RepositoryModuleTest : MockkUnitTest() {

    private lateinit var repositoryModule: RepositoryModule

    override fun onCreate() {
        super.onCreate()
        repositoryModule = RepositoryModule()
    }

    @Test
    fun verifyProvideHomeRepository() {
        val service = mockk<GithubService>()
        val database = mockk<AppDatabase>()
        val userDao = mockk<UserDao>()
        val remoteKeysDao = mockk<RemoteKeysDao>()
        val repository = repositoryModule.provideHomeRepository(service, database, userDao, remoteKeysDao)

        Assert.assertEquals(service, repository.githubService)
        Assert.assertEquals(database, repository.appDatabase)
        Assert.assertEquals(userDao, repository.userDao)
        Assert.assertEquals(remoteKeysDao, repository.remoteKeysDao)
    }

    @Test
    fun verifyProvideDetailRepository() {
        val service = mockk<GithubService>()
        val dispatcher = Dispatchers.Default
        val repository = repositoryModule.provideDetailRepository(service, dispatcher)

        Assert.assertEquals(service, repository.githubService)
        Assert.assertEquals(dispatcher, repository.dispatcher)
    }
}