package com.dienty.common.local.dao

import androidx.paging.PagingSource
import androidx.room.Room
import com.dienty.common.local.db.AppDatabase
import com.dienty.common.local.mockdata.LocalMockData
import com.dienty.core.testing.TestRobolectric
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import java.io.IOException

class UserDaoTest : TestRobolectric() {

    private lateinit var database: AppDatabase

    private lateinit var dao: UserDao

    override fun onCreate() {
        super.onCreate()

        runTest {
            database = Room
                .inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                .allowMainThreadQueries()
                .build()
            dao = database.getUserDao()
        }
    }

    @Throws(IOException::class)
    override fun onDestroy() {
        super.onDestroy()

        database.close()
    }

    @Test
    fun getUsers_WithData() = runTest {
        val fakeUsers = LocalMockData.getUsers()
        dao.insertAll(fakeUsers)

        val users = dao.getUsers()
    }
}