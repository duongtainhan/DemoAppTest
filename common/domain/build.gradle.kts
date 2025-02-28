import extensions.COMMON_MODEL
import extensions.COMMON_REPOSITORY
import extensions.CORE_NETWORK
import extensions.CORE_STRUCTURE
import extensions.CORE_USECASE
import extensions.implementation

plugins {
    id("commons.android-library")
    id("commons.dagger-hilt")
    id("com.google.devtools.ksp")
    id("codeanalyzetools.jacoco-report")
}

android {
    externalNativeBuild {
        cmake {
            path = File("${projectDir}/CMakeLists.txt")
        }
    }
    ndkVersion = "22.1.7171670"
}

dependencies {
    CORE_STRUCTURE
    CORE_USECASE
    COMMON_REPOSITORY
    COMMON_MODEL
    CORE_NETWORK
    implementation(SupportLib.Paging)
}