plugins {
    id("commons.android-library")
}

dependencies {
    implementation(SupportLib.CoreKtx)
    implementation(SupportLib.Appcompat)
    implementation(NetworkLib.Moshi)
    implementation(SupportLib.CoroutineCore)
    implementation(SupportLib.LifecycleRuntime)
}