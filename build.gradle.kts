// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    id("com.android.application") version Build.androidGradlePluginVersion apply false
    id("com.android.library") version Build.androidGradlePluginVersion apply false
    id("com.google.dagger.hilt.android") version DaggerHilt.version apply false

    kotlin("plugin.serialization") version Kotlin.version
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}