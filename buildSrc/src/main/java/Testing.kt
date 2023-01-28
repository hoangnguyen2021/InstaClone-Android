object Testing {
    private const val junitVersion = "4.13.2"
    private const val junitAndroidExtVersion = "1.1.4"
    private const val truthVersion = "1.1.3"
    private const val mockkVersion = "1.13.3"
    private const val turbineVersion = "0.12.1"
    private const val mockWebServerVersion = "4.10.0"
    private const val testRunnerVersion = "1.5.0"

    const val junit4 = "junit:junit:$junitVersion"
    const val junitAndroidExt = "androidx.test.ext:junit:$junitAndroidExtVersion"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Kotlinx.coroutinesVersion}"
    const val truth = "com.google.truth:truth:$truthVersion"
    const val mockk = "io.mockk:mockk:$mockkVersion"
    const val mockkAndroid = "io.mockk:mockk-android:$mockkVersion"
    const val turbine = "app.cash.turbine:turbine:$turbineVersion"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$mockWebServerVersion"
    const val composeUiTest = "androidx.compose.ui:ui-test-junit4:${Compose.version}"
    const val hiltTesting = "com.google.dagger:hilt-android-testing:${DaggerHilt.version}"
    const val testRunner = "androidx.test:runner:$testRunnerVersion"
}