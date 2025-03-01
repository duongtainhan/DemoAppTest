package com.dienty.common.model.response

import com.dienty.core.testing.BaseModelTest
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class DetailResponseTest : BaseModelTest() {

    override fun checkModelClass(): Class<*> {
        return DetailResponse::class.java
    }

    override fun checkModelFields(): List<String> {
        return listOf(
            "login",
            "avatar_url",
            "html_url",
            "location",
            "followers",
            "following"
        )
    }

    @Test
    fun createResponse() {
        val response: DetailResponse = mockk()
        Assert.assertNotNull(response)
    }
}