package com.dienty.architecture.app

import com.dienty.architecture.BuildConfig
import com.dienty.core.structure.app.NetworkConfig

class AppNetworkConfig : NetworkConfig() {
    override fun timeOutMs() = 20000L
    override fun isDev() = BuildConfig.DEBUG
}