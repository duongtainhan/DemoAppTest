package com.dienty.common.model.response

import com.dienty.core.testing.BaseModelTest
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class UserResponseTest : BaseModelTest() {

    override fun checkModelClass(): Class<*> {
        return UserResponse::class.java
    }

    override fun checkModelFields(): List<String> {
        return listOf(
            "login",
            "avatar_url",
            "html_url"
        )
    }

    @Test
    fun createResponse() {
        val response: UserResponse = mockk()
        Assert.assertNotNull(response)
    }
}