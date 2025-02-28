package com.dienty.common.repository.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoroutineModule {

    @Provides
    @Singleton
    fun provideIoDispatcher() = Dispatchers.IO

    @Provides
    @Singleton
    @Named("dispatcher_default")
    fun provideDefaultDispatcher() = Dispatchers.Default

    @Provides
    @Singleton
    fun provideCoroutineScope(
        @Named("dispatcher_default") dispatcher: CoroutineDispatcher
    ) = CoroutineScope(SupervisorJob() + dispatcher)
}