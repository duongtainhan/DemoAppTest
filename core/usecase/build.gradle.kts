import extensions.CORE_NETWORK

plugins {
    id("commons.android-library")
}

dependencies {
    CORE_NETWORK
    implementation(SupportLib.Paging)
    implementation(SupportLib.CoroutineCore)
}