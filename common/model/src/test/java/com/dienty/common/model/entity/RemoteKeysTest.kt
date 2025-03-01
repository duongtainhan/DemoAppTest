package com.dienty.common.model.entity

import org.junit.Assert
import org.junit.Test

class RemoteKeysTest {

    @Test
    fun checkCorrectAttributes() {
        val username = "Dienty"
        val prevKey = 0
        val nextKey = 1
        val currentPage = 1
        val createdAt = System.currentTimeMillis()

        val entity = RemoteKeys(
            username = username,
            prevKey = prevKey,
            nextKey = nextKey,
            currentPage = currentPage,
            createdAt = createdAt
        )

        Assert.assertEquals(username, entity.username)
        Assert.assertEquals(prevKey, entity.prevKey)
        Assert.assertEquals(nextKey, entity.nextKey)
        Assert.assertEquals(currentPage, entity.currentPage)
        Assert.assertEquals(createdAt, entity.createdAt)
    }

}