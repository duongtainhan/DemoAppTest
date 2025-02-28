package com.dienty.core.structure.app

import com.dienty.core.structure.BuildConfig

abstract class NetworkConfig {

    abstract fun timeOutMs(): Long

    open fun isDev(): Boolean = BuildConfig.DEBUG
}