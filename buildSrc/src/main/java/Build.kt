object Build {
    private const val androidGradlePluginVersion = "7.3.1"

    const val androidGradlePlugin = "com.android.tools.build:gradle:$androidGradlePluginVersion"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
    const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${DaggerHilt.version}"
}