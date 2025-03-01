package com.dienty.common.local.dao

import androidx.room.Room
import com.dienty.common.local.db.AppDatabase
import com.dienty.common.local.mockdata.LocalMockData
import com.dienty.core.testing.TestRobolectric
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import java.io.IOException

class RemoteKeysDaoTest : TestRobolectric() {

    private lateinit var database: AppDatabase

    private lateinit var dao: RemoteKeysDao

    override fun onCreate() {
        super.onCreate()

        runTest {
            database = Room
                .inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                .allowMainThreadQueries()
                .build()
            dao = database.getRemoteKeysDao()
        }
    }

    @Throws(IOException::class)
    override fun onDestroy() {
        super.onDestroy()

        database.close()
    }

    @Test
    fun getRemoteKeys_WithData() = runTest {
        val fakeRemoteKeys = LocalMockData.getRemoteKeys()
        dao.insertAll(fakeRemoteKeys)
        val remoteKeys = dao.getRemoteKeys()
        Assert.assertEquals(fakeRemoteKeys, remoteKeys)
    }

    @Test
    fun getRemoteKeys_WithoutData() = runTest {
        val users = dao.getRemoteKeys()
        Assert.assertTrue(users.isEmpty())
    }

    @Test
    fun getRemoteKeysByUsername_WithoutData_ShouldNotFound() = runTest {
        val fakeRemoteKeys = LocalMockData.getRemoteKeys()
        val remoteKeyToFind = fakeRemoteKeys.first()
        Assert.assertNull(dao.getRemoteKeyByUsername(remoteKeyToFind.username))
    }

    @Test
    fun getRemoteKeysByUsername_WithData_ShouldFound() = runTest {
        val fakeRemoteKeys = LocalMockData.getRemoteKeys()
        dao.insertAll(fakeRemoteKeys)
        val remoteKeyToFind = fakeRemoteKeys.first()
        Assert.assertEquals(remoteKeyToFind, dao.getRemoteKeyByUsername(remoteKeyToFind.username))
    }

    @Test
    fun deleteRemoteKeysList_ShouldRemoveAll() = runTest {
        val fakeRemoteKeys = LocalMockData.getRemoteKeys()
        dao.insertAll(fakeRemoteKeys)
        dao.clearRemoteKeys()

        Assert.assertTrue(dao.getRemoteKeys().isEmpty())
    }
}