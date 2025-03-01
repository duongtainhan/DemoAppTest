package com.dienty.common.model.entity

import org.junit.Assert
import org.junit.Test

class UserEntityTest {

    @Test
    fun checkCorrectAttributes() {
        val username = "Dienty"
        val avatarUrl = "www.facebook.com/avatar/dienty"
        val htmlUrl = "www.facebook.com/dienty"
        val page = 1

        val entity =  UserEntity (
            username = username,
            avatarUrl = avatarUrl,
            htmlUrl = htmlUrl,
            page = page
        )

        Assert.assertEquals(username, entity.username)
        Assert.assertEquals(avatarUrl, entity.avatarUrl)
        Assert.assertEquals(htmlUrl, entity.htmlUrl)
        Assert.assertEquals(page, entity.page)
    }

}