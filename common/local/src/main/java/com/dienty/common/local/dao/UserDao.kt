package com.dienty.common.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dienty.common.model.entity.UserEntity
import com.dienty.core.local.room.BaseDao

@Dao
interface UserDao : BaseDao<UserEntity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<UserEntity>)

    @Query("Select * From user_entity Order By page")
    fun getUsers(): PagingSource<Int, UserEntity>

    @Query("Delete From user_entity")
    suspend fun clearAllUsers()
}