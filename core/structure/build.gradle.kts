plugins {
    id("commons.android-library")
}

android {
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(SupportLib.CoroutineCore)
    implementation(SupportLib.CoroutineAndroid)
    implementation(SupportLib.LifecycleRuntime)
    implementation(SupportLib.Timber)
    implementation(SupportLib.CoreKtx)
    implementation(SupportLib.Appcompat)
    implementation(SupportLib.LifecycleViewModel)
    implementation(DaggerHiltLib.Android)
}