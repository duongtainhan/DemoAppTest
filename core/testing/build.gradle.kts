plugins {
    id("commons.android-library")
}

dependencies {
    api("junit:junit:4.13.2")
    api("androidx.test.ext:junit:1.1.3")
    api("androidx.test.espresso:espresso-core:3.4.0")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.3")
    api("com.google.truth:truth:1.1.3")
    api("org.robolectric:robolectric:4.7.3")
    api("app.cash.turbine:turbine:0.7.0")
    api("io.mockk:mockk:1.12.3")
    api("com.squareup.okhttp3:mockwebserver:5.0.0-alpha.6")
    api("org.hamcrest:hamcrest-library:2.2")
    implementation("com.squareup.moshi:moshi-kotlin:1.13.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.6")
}