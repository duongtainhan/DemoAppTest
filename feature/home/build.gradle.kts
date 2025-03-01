import extensions.COMMON_USECASE
import extensions.COMMON_MODEL
import extensions.COMMON_RESOURCE
import extensions.CORE_EXTENSION
import extensions.CORE_STRUCTURE
import extensions.CORE_USECASE
import extensions.addCommonDependencies
import extensions.implementation

plugins {
    id("commons.android-feature")
    id("commons.dagger-hilt")
}

android {
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    CORE_STRUCTURE
    addCommonDependencies()
    COMMON_USECASE
    CORE_USECASE
    COMMON_MODEL
    COMMON_RESOURCE
    CORE_EXTENSION
    implementation(SupportLib.Paging)
    implementation(SupportLib.Glide)
}