package com.dienty.common.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dienty.common.local.dao.RemoteKeysDao
import com.dienty.common.local.dao.UserDao
import com.dienty.common.model.entity.RemoteKeys
import com.dienty.common.model.entity.UserEntity

@Database(
    entities = [UserEntity::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getUserDao(): UserDao

    abstract fun getRemoteKeysDao(): RemoteKeysDao
}