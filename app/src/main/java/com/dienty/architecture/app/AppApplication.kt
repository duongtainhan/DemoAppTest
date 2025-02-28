package com.dienty.architecture.app

import com.dienty.core.structure.app.AppInitializer
import com.dienty.core.structure.app.CoreApplication
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class AppApplication: CoreApplication() {
    @Inject
    lateinit var initializer: AppInitializer

    override fun onCreate() {
        super.onCreate()

        initializer.init(this)
    }
}