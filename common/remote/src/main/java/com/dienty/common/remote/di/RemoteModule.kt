package com.dienty.common.remote.di

import android.content.Context
import com.dienty.common.remote.service.GithubService
import com.dienty.core.network.createMoshi
import com.dienty.core.network.createOkHttpClient
import com.dienty.core.network.createRetrofitWithMoshi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = createMoshi()

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context) =
        createOkHttpClient(context = context, isCache = true, authenticator = AppAuthenticator())

    @Provides
    @Singleton
    fun provideGithubService(@Named("DOMAIN_HOST") host: String, client: OkHttpClient, moshi: Moshi) =
        createRetrofitWithMoshi<GithubService>(okHttpClient = client, moshi = moshi, baseUrl = host)

}