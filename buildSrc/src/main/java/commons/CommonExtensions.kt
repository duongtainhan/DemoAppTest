package commons

import com.android.build.api.dsl.CommonExtension
/**
 * Adds the base default app configurations on Gradle.
 */
fun CommonExtension<*, *, *, *>.addDefaultConfig() {
    defaultConfig {
        compileSdk = Configs.CompileSdk
        minSdk = Configs.MinSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}