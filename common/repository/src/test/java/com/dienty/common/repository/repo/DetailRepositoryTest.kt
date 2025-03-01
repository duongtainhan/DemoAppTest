package com.dienty.common.repository.repo

import com.dienty.common.remote.service.GithubService
import com.dienty.common.repository.detail.DetailRepository
import com.dienty.core.testing.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class DetailRepositoryTest  : MockkUnitTest() {

    @MockK(relaxed = true)
    lateinit var service: GithubService

    private lateinit var repository: DetailRepository

    override fun onCreate() {
        super.onCreate()
        repository = DetailRepository(service, Dispatchers.Default)
    }

    @Test
    fun getCharacters() = runTest {
        // Given
        val username = "pjhyett"
        val usernameSlot = slot<String>()

        // When
        repository.getDetail(username = username)

        // Then
        coVerify {
            service.getDetail(username = capture(usernameSlot))
        }

        Assert.assertEquals(username, usernameSlot.captured)
    }
}