import extensions.*

plugins {
    id("commons.android-library")
    id("commons.dagger-hilt")
    id("com.google.devtools.ksp")
    id("codeanalyzetools.jacoco-report")
}

dependencies {
    COMMON_MODEL
    CORE_LOCAL
    CORE_EXTENSION
    implementation(StorageLib.RoomKtx)
    ksp(StorageLib.RoomCompiler)
    implementation(SupportLib.Paging)
    implementation(StorageLib.RoomPaging)
}