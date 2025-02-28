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
    // Network
    addNetworkDependencies()
}