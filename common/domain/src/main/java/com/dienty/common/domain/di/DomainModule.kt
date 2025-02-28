package com.dienty.common.domain.di

import com.dienty.common.domain.di.DomainKey.DATABASE_NAME
import com.dienty.common.domain.di.DomainKey.DOMAIN_HOST
import com.dienty.common.domain.usecase.GetDetailUseCase
import com.dienty.common.domain.usecase.GetUsersUseCase
import com.dienty.common.repository.detail.DetailRepository
import com.dienty.common.repository.home.HomeRepository
import com.dienty.core.structure.app.NetworkConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    init {
        System.loadLibrary("native-lib")
    }

    private external fun decodeDatabaseName(dev: Boolean): String
    private external fun decodeDomainHost(dev: Boolean): String

    @Provides
    @Singleton
    @Named(DOMAIN_HOST)
    fun provideDomainHost(config: NetworkConfig) = decodeDomainHost(dev = config.isDev())

    @Provides
    @Singleton
    @Named(DATABASE_NAME)
    fun provideDatabase(config: NetworkConfig) = decodeDatabaseName(dev = config.isDev())

    @Singleton
    @Provides
    fun provideGetUserUseCase(repository: HomeRepository): GetUsersUseCase {
        return GetUsersUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetDetailUseCase(repository: DetailRepository): GetDetailUseCase {
        return GetDetailUseCase(repository)
    }
}