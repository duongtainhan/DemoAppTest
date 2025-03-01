package com.dienty.common.remote.di

import com.dienty.common.remote.service.GithubService
import com.dienty.core.structure.app.NetworkConfig
import com.dienty.core.testing.TestRobolectric
import com.squareup.moshi.Moshi
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit

class RemoteModuleTest : TestRobolectric() {

    private lateinit var remoteModule: RemoteModule
    private lateinit var networkConfig: NetworkConfig

    override fun onCreate() {
        super.onCreate()
        networkConfig = object : NetworkConfig() {
            override fun timeOutMs() = 30_000L
        }
        remoteModule = RemoteModule()
    }

//    @Test
//    fun verifyBaseUrl() {
//        val baseUrl = remoteModule.provideDomainHost(networkConfig)
//        Assert.assertEquals(baseUrl, "https://api.github.com/")
//    }
//
//    @Test
//    fun verifyProvideOkHttpClient() {
//        val authenticatorInterceptor = mockk<Authenticator>()
//        val httpClient = remoteModule.provideOkHttpClient(context, authenticatorInterceptor)
//
//        Assert.assertEquals(1, httpClient.interceptors.size)
//    }
//
//    @Test
//    fun verifyProvideGithubService() {
//        val baseUrl = remoteModule.provideDomainHost(networkConfig)
//        val httpClient = mockk<OkHttpClient>()
//        val moshi = mockk<Moshi>()
//        val retrofit = mockk<Retrofit>()
//        val service = mockk<GithubService>()
//        val serviceClassCaptor = slot<Class<*>>()
//
//        every { retrofit.create<GithubService>(any()) } returns service
//
//        remoteModule.provideGithubService(baseUrl, httpClient, moshi)
//
//        verify { retrofit.create(capture(serviceClassCaptor)) }
//        Assert.assertEquals(GithubService::class.java, serviceClassCaptor.captured)
//    }
}