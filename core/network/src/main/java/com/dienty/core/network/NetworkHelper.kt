package com.dienty.core.network

import android.content.Context
import com.dienty.core.network.logging.CurlLoggingInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Authenticator
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

private const val CLIENT_TIME_OUT = 60L
private const val CLIENT_CACHE_SIZE = 10 * 1024 * 1024L
private const val CLIENT_CACHE_DIRECTORY = "http"

fun createMoshi(): Moshi {
    return Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
}

fun createCache(context: Context): Cache = Cache(
    directory = File(context.cacheDir, CLIENT_CACHE_DIRECTORY),
    maxSize = CLIENT_CACHE_SIZE
)

fun createCurlLoggingInterceptor() = CurlLoggingInterceptor()

fun createAuthInterceptor(token: String) = Interceptor {
    return@Interceptor it.proceed(
        it.request().newBuilder().addHeader("Authorization", token).build()
    )
}

fun createOkHttpClient(
    context: Context,
    token: String = "",
    isCache: Boolean = false,
    authenticator: Authenticator = Authenticator.NONE,
    interceptors: MutableList<Interceptor> = mutableListOf()
): OkHttpClient {
    return OkHttpClient.Builder().apply {
        if (isCache) cache(createCache(context))
        if (BuildConfig.DEBUG) {
            addInterceptor(createCurlLoggingInterceptor())
        }
        if (interceptors.isNotEmpty()) {
            interceptors.forEach { addInterceptor(it) }
        }
        authenticator(authenticator)
        if (token.isNotBlank()) {
            addInterceptor(createAuthInterceptor(token))
        }
        connectTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        readTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        writeTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        followSslRedirects(true)
        followRedirects(true)
        retryOnConnectionFailure(true)
    }.build()
}

/**
 * Create Retrofit Client with Moshi
 *
 * <reified T> private func let us using reflection.
 * We can use generics and reflection so ->
 *  we don't have to define required NewsApi Interface here
 */
inline fun <reified T> createRetrofitWithMoshi(
    okHttpClient: OkHttpClient,
    moshi: Moshi,
    baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
    return retrofit.create(T::class.java)
}