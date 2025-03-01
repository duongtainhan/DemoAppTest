package com.dienty.common.remote.service

import com.dienty.common.remote.mock.MockResponses
import com.dienty.core.testing.BaseServiceTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class GithubServiceTest  : BaseServiceTest<GithubService>(GithubService::class) {

    override val baseUrl: String get() = "https://api.github.com/"

    @Test
    fun requestLiveGetUsers() = runTest {
        val response = serviceLive.getUsers(perPage = 20, since = 0)
        Assert.assertEquals("mojombo", response?.first()?.login)
        Assert.assertEquals(
            "https://avatars.githubusercontent.com/u/1?v=4",
            response?.first()?.avatarUrl
        )
        Assert.assertEquals("https://github.com/mojombo", response?.first()?.htmlUrl)
    }

    @Test
    fun requestGetUsers() = runTest {
        enqueueResponse(MockResponses.GetUsers.STATUS_200)
        serviceMock.getUsers(perPage = 20, since = 0)
        val request = mockWebServer.takeRequest()
        Assert.assertEquals("GET", request.method)
        Assert.assertEquals("/users?per_page=20&since=0", request.path)
    }

    @Test
    fun responseGetUsers() = runTest {
        enqueueResponse(MockResponses.GetUsers.STATUS_200)
        val response = serviceMock.getUsers(perPage = 20, since = 0)
        Assert.assertEquals("mojombo", response?.first()?.login)
        Assert.assertEquals(
            "https://avatars.githubusercontent.com/u/1?v=4",
            response?.first()?.avatarUrl
        )
        Assert.assertEquals("https://github.com/mojombo", response?.first()?.htmlUrl)
    }

    @Test
    fun requestLiveGetDetail() = runTest {
        val response = serviceLive.getDetail(username = "pjhyett")
        Assert.assertEquals("pjhyett", response?.body()?.login)
        Assert.assertEquals("https://avatars.githubusercontent.com/u/3?v=4", response?.body()?.avatarUrl)
        Assert.assertEquals("https://github.com/pjhyett", response?.body()?.htmlUrl)
        Assert.assertEquals(null, response?.body()?.location)
        Assert.assertEquals(8321, response?.body()?.followers)
        Assert.assertEquals(30, response?.body()?.following)
    }

    @Test
    fun requestGetDetail() = runTest {
        enqueueResponse(MockResponses.GetDetailByUsername.STATUS_200)
        serviceMock.getDetail(username = "pjhyett")
        val request = mockWebServer.takeRequest()
        Assert.assertEquals("GET", request.method)
        Assert.assertEquals("/users/pjhyett", request.path)
    }

    @Test
    fun responseGetDetail() = runTest {
        enqueueResponse(MockResponses.GetDetailByUsername.STATUS_200)
        val response = serviceMock.getDetail("pjhyett")
        Assert.assertEquals("pjhyett", response?.body()?.login)
        Assert.assertEquals("https://avatars.githubusercontent.com/u/3?v=4", response?.body()?.avatarUrl)
        Assert.assertEquals("https://github.com/pjhyett", response?.body()?.htmlUrl)
        Assert.assertEquals(null, response?.body()?.location)
        Assert.assertEquals(8321, response?.body()?.followers)
        Assert.assertEquals(30, response?.body()?.following)
    }
}