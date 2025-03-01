package com.dienty.common.usecase.di

import com.dienty.common.repository.detail.DetailRepository
import com.dienty.common.repository.home.HomeRepository
import com.dienty.common.usecase.usecase.GetDetailUseCase
import com.dienty.common.usecase.usecase.GetUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

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