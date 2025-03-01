package com.dienty.common.remote.di

import android.content.Context
import com.dienty.common.remote.service.GithubService
import com.dienty.core.network.createMoshi
import com.dienty.core.network.createOkHttpClient
import com.dienty.core.network.createRetrofitWithMoshi
import com.dienty.core.structure.app.NetworkConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

private const val DOMAIN_HOST = "DOMAIN_HOST"

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    init {
        System.loadLibrary("native-lib")
    }

    private external fun decodeDomainHost(dev: Boolean): String

    @Provides
    @Singleton
    @Named(DOMAIN_HOST)
    fun provideDomainHost(config: NetworkConfig) = decodeDomainHost(dev = config.isDev())

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = createMoshi()

    @Provides
    @Singleton
    fun provideAuthenticator(): Authenticator {
        return AppAuthenticator()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context, authenticator: Authenticator) =
        createOkHttpClient(context = context, isCache = true, authenticator = authenticator)

    @Provides
    @Singleton
    fun provideGithubService(@Named(DOMAIN_HOST) host: String, client: OkHttpClient, moshi: Moshi) =
        createRetrofitWithMoshi<GithubService>(okHttpClient = client, moshi = moshi, baseUrl = host)

}