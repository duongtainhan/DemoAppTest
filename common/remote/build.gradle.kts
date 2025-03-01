import extensions.*

plugins {
    id("commons.android-library")
    id("commons.dagger-hilt")
    id("com.google.devtools.ksp")
    id("codeanalyzetools.jacoco-report")
}

dependencies {
    COMMON_MODEL
    CORE_NETWORK
    CORE_STRUCTURE
    // Network
    addNetworkDependencies()
}
android {
    externalNativeBuild {
        cmake {
            path = File("${projectDir}/CMakeLists.txt")
        }
    }
    ndkVersion = "22.1.7171670"
}
