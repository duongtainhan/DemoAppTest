import extensions.CORE_EXTENSION
import extensions.implementation
import extensions.ksp

plugins {
    id("commons.android-library")
    id("com.google.devtools.ksp")
    id("codeanalyzetools.jacoco-report")
}

dependencies {
    CORE_EXTENSION
    implementation(NetworkLib.Moshi)
    ksp(NetworkLib.MoshiCodegen)
    implementation(NetworkLib.MoshiLazyAdapter)
    implementation(StorageLib.RoomKtx)
    ksp(StorageLib.RoomCompiler)
}