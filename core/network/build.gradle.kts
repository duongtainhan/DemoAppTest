plugins {
    id("commons.android-library")
    id("commons.dagger-hilt")
}

dependencies {
    implementation(SupportLib.CoroutineCore)
    implementation(NetworkLib.Moshi)
    implementation(NetworkLib.MoshiLazyAdapter)
    implementation(NetworkLib.Retrofit)
    implementation(NetworkLib.RetrofitMoshi)
    implementation(NetworkLib.Okhttp)
    implementation(NetworkLib.LoggingInterceptor)
}