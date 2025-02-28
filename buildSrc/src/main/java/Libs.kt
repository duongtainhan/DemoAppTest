object Versions {
    const val CoreKtx = "1.8.0"
    const val AppCompat = "1.4.1"
    const val Material = "1.5.0"
    const val Coroutines = "1.6.1"
    const val Lifecycle = "2.4.1"
    const val KtxFragment = "1.4.1"
    const val KtxActivity = "1.4.0"
    const val SplashScreen = "1.0.0-beta02"
    const val Timber = "5.0.1"
    const val Paging = "3.1.1"
    const val Glide = "4.16.0"
    const val Moshi = "1.13.0"
    const val MoshiAdapter = "2.2"
    const val Retrofit = "2.9.0"
    const val OkHttp = "5.0.0-alpha.5"
    const val Room = "2.4.2"
    const val Datastore = "1.0.0"
    const val Crypto = "1.1.0-alpha03"
    const val HiltDagger = "2.41"
    const val HiltFragment = "1.0.0"
    const val HiltCompiler = "1.0.0"
    const val Junit = "4.13.2"
    const val TestingCoroutine = "1.6.0"
    const val Truth = "1.1.3"
    const val Robolectric = "4.7.3"
    const val Turbine = "0.7.0"
    const val Mockk = "1.12.2"
    const val JunitExt = "1.1.3"
    const val EspressoCore = "3.4.0"
}

object SupportLib {
    const val CoreKtx = "androidx.core:core-ktx:${Versions.CoreKtx}"
    const val Appcompat = "androidx.appcompat:appcompat:${Versions.AppCompat}"
    const val Material = "com.google.android.material:material:${Versions.Material}"
    const val CoroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Coroutines}"
    const val CoroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Coroutines}"
    const val LifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Lifecycle}"
    const val LifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Lifecycle}"
    const val ActivityKtx = "androidx.activity:activity-ktx:${Versions.KtxActivity}"

    const val Splashscreen = "androidx.core:core-splashscreen:${Versions.SplashScreen}"
    const val Timber = "com.jakewharton.timber:timber:${Versions.Timber}"
    const val Paging = "androidx.paging:paging-runtime:${Versions.Paging}"
    const val Glide = "com.github.bumptech.glide:glide:${Versions.Glide}"
}

object NetworkLib {
    const val Moshi = "com.squareup.moshi:moshi-kotlin:${Versions.Moshi}"
    const val MoshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.Moshi}"
    const val MoshiLazyAdapter = "com.serjltt.moshi:moshi-lazy-adapters:${Versions.MoshiAdapter}"
    const val Retrofit = "com.squareup.retrofit2:retrofit:${Versions.Retrofit}"
    const val RetrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.Retrofit}"
    const val Okhttp = "com.squareup.okhttp3:okhttp:${Versions.OkHttp}"
    const val LoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.OkHttp}"

    const val KotlinXSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2"
    const val KotlinXSerializationRetrofit =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
}

object StorageLib {
    const val RoomKtx = "androidx.room:room-ktx:${Versions.Room}"
    const val RoomPaging = "androidx.room:room-paging:${Versions.Room}"
    const val RoomCompiler = "androidx.room:room-compiler:${Versions.Room}"
    const val DatastorePref = "androidx.datastore:datastore-preferences:${Versions.Datastore}"
    const val Datastore = "androidx.datastore:datastore:${Versions.Datastore}"
    const val SecurityPref = "androidx.security:security-crypto:${Versions.Crypto}"
}


object DaggerHiltLib {
    const val Android = "com.google.dagger:hilt-android:${Versions.HiltDagger}"
    const val AndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.HiltDagger}"
    const val Compiler = "androidx.hilt:hilt-compiler:${Versions.HiltCompiler}"
}

object TestingLib {
    const val Junit = "junit:junit:${Versions.Junit}"
    const val Coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.TestingCoroutine}"
    const val Truth = "com.google.truth:truth:${Versions.Truth}"
    const val Robolectric = "org.robolectric:robolectric:${Versions.Robolectric}"
    const val Turbine = "app.cash.turbine:turbine:${Versions.Turbine}"
    const val Mockk = "io.mockk:mockk:${Versions.Mockk}"
    const val Okhttp = "com.squareup.okhttp3:mockwebserver:${Versions.OkHttp}"
}

object AndroidTestingLib {
    const val JunitExt = "androidx.test.ext:junit:${Versions.JunitExt}"
    const val EspressoCore = "androidx.test.espresso:espresso-core:${Versions.EspressoCore}"
}