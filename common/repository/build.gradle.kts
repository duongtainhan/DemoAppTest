import extensions.*

plugins {
    id("commons.android-library")
    id("commons.dagger-hilt")
    id("codeanalyzetools.jacoco-report")
}

dependencies {
    COMMON_MODEL
    COMMON_LOCAL
    COMMON_REMOTE
    CORE_NETWORK
    CORE_LOCAL
    implementation(StorageLib.DatastorePref)
    implementation(SupportLib.Paging)
    implementation(NetworkLib.Retrofit)
    implementation(StorageLib.RoomKtx)
}