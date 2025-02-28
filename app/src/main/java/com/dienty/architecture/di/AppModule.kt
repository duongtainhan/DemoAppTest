package com.dienty.architecture.di

import com.dienty.core.structure.app.*
import com.dienty.architecture.app.AppNetworkConfig
import com.dienty.architecture.app.AppApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideApplication(): CoreApplication = AppApplication()

    @Provides
    @Singleton
    fun provideNetworkConfig(): NetworkConfig = AppNetworkConfig()

    @Provides
    @Singleton
    fun provideTimberInitializer(networkConfig: NetworkConfig) =
        TimberInitializer(networkConfig.isDev())


    @Provides
    @Singleton
    fun provideAppInitializer(timberInitializer: TimberInitializer): AppInitializer {
        return AppInitializerImpl(timberInitializer)
    }
}