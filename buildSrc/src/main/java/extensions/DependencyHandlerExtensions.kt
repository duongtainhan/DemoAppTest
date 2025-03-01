package extensions

import NetworkLib
import StorageLib
import SupportLib
import TestingLib
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * Adds a dependency to the `releaseImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.releaseImplementation(dependencyNotation: Any): Dependency? =
    add("releaseImplementation", dependencyNotation)

/**
 * Adds a dependency to the `debugImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.debugImplementation(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)

/**
 * Adds a dependency to the `implementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

/**
 * Adds a dependency to the `api` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

/**
 * Adds a dependency to the `kapt` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

/**
 * Adds a dependency to the `testImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)


/**
 * Adds a dependency to the `androidTestImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

/**
 * Adds a dependency to the `ksp` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.ksp(dependencyNotation: Any): Dependency? =
    add("ksp", dependencyNotation)

fun DependencyHandler.addCommonDependencies() {
    implementation(SupportLib.CoreKtx)
    implementation(SupportLib.Appcompat)
    implementation(SupportLib.Material)
    implementation(SupportLib.CoroutineCore)
    implementation(SupportLib.CoroutineAndroid)
    implementation(SupportLib.LifecycleRuntime)
    implementation(SupportLib.ActivityKtx)
}

fun DependencyHandler.addNetworkDependencies() {
    // Network
    implementation(NetworkLib.Moshi)
    ksp(NetworkLib.MoshiCodegen)
    implementation(NetworkLib.MoshiLazyAdapter)
    implementation(NetworkLib.Retrofit)
    implementation(NetworkLib.RetrofitMoshi)
    implementation(NetworkLib.Okhttp)
    implementation(NetworkLib.LoggingInterceptor)
    testImplementation(TestingLib.Okhttp)
}

fun DependencyHandler.addStorageDependencies() {
    // Storage
    implementation(StorageLib.RoomKtx)
    ksp(StorageLib.RoomCompiler)
    implementation(StorageLib.DatastorePref)
    implementation(StorageLib.Datastore)
}

fun DependencyHandler.addTestDependencies() {
    CORE_TEST
}

fun DependencyHandler.addFeatureModule() {
    FEATURE_HOME
    FEATURE_DETAIL
}

fun DependencyHandler.addCoreModule() {
    CORE_EXTENSION
    CORE_LOCAL
    CORE_NETWORK
    CORE_STRUCTURE
    CORE_TEST
    CORE_USECASE
}

fun DependencyHandler.addCommonModule() {
    COMMON_USECASE
    COMMON_LOCAL
    COMMON_MODEL
    COMMON_REMOTE
    COMMON_REPOSITORY
    COMMON_RESOURCE
}

// Feature
val DependencyHandler.FEATURE_HOME
    get() = implementation(project(mapOf("path" to ":feature:home")))
val DependencyHandler.FEATURE_DETAIL
    get() = implementation(project(mapOf("path" to ":feature:detail")))

// Core
val DependencyHandler.CORE_EXTENSION
    get() = implementation(project(mapOf("path" to ":core:extension")))
val DependencyHandler.CORE_LOCAL
    get() = implementation(project(mapOf("path" to ":core:local")))
val DependencyHandler.CORE_NETWORK
    get() = implementation(project(mapOf("path" to ":core:network")))
val DependencyHandler.CORE_STRUCTURE
    get() = implementation(project(mapOf("path" to ":core:structure")))
val DependencyHandler.CORE_USECASE
    get() = implementation(project(mapOf("path" to ":core:usecase")))
val DependencyHandler.CORE_TEST
    get() = testImplementation(project(mapOf("path" to ":core:testing")))

// Common
val DependencyHandler.COMMON_MODEL
    get() = implementation(project(mapOf("path" to ":common:model")))
val DependencyHandler.COMMON_USECASE
    get() = implementation(project(mapOf("path" to ":common:usecase")))
val DependencyHandler.COMMON_REMOTE
    get() = implementation(project(mapOf("path" to ":common:remote")))
val DependencyHandler.COMMON_LOCAL
    get() = implementation(project(mapOf("path" to ":common:local")))
val DependencyHandler.COMMON_REPOSITORY
    get() = implementation(project(mapOf("path" to ":common:repository")))
val DependencyHandler.COMMON_RESOURCE
    get() = implementation(project(mapOf("path" to ":common:resource")))