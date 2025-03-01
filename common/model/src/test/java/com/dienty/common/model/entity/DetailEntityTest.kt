package com.dienty.common.model.entity

import org.junit.Assert
import org.junit.Test

class DetailEntityTest {

    @Test
    fun checkCorrectAttributes() {
        val username = "Dienty"
        val avatarUrl = "www.facebook.com/avatar/dienty"
        val htmlUrl = "www.facebook.com/dienty"
        val location = "District 2, Thu Duc City"
        val followers = 369
        val followings = 963

        val entity =  DetailEntity (
            username = username,
            avatarUrl = avatarUrl,
            htmlUrl = htmlUrl,
            location = location,
            followers = followers,
            followings = followings
        )

        Assert.assertEquals(username, entity.username)
        Assert.assertEquals(avatarUrl, entity.avatarUrl)
        Assert.assertEquals(htmlUrl, entity.htmlUrl)
        Assert.assertEquals(location, entity.location)
        Assert.assertEquals(followers, entity.followers)
        Assert.assertEquals(followings, entity.followings)
    }

}