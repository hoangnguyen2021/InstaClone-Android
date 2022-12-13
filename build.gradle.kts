// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Build.androidGradlePlugin)
        classpath(Build.hiltAndroidGradlePlugin)
        classpath(Build.kotlinGradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

//plugins {
//    id 'com.android.application' version "7.3.1" apply false
//    id 'com.android.library' version "7.3.1" apply false
//    id 'org.jetbrains.kotlin.android' version "1.6.10" apply false
//}