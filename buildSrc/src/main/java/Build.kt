object Build {
    const val androidGradlePluginVersion = "7.4.2"
    private const val desugarJdkLibsVersion = "1.2.2"

    const val androidGradlePlugin = "com.android.tools.build:gradle:$androidGradlePluginVersion"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
    const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${DaggerHilt.version}"

    const val desugarJdkLibs = "com.android.tools:desugar_jdk_libs:$desugarJdkLibsVersion"
}