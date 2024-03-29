plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("kotlinx-serialization")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "myapp.hoang.instaclone"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.compilerVersion
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/AL2.0")
            excludes.add("/META-INF/LGPL2.1")
            excludes.add("META-INF/DEPENDENCIES")
        }
    }
}

dependencies {
    implementation(project(Module.core))
    implementation(project(Module.coreUi))
    implementation(project(Module.onBoarding))
    implementation(project(Module.media))
    implementation(project(Module.settings))
    implementation(project(Module.users))

    implementation(Kotlinx.coroutines)
    implementation(Kotlinx.serialization)
    implementation(Kotlinx.dateTime)
    implementation(Kotlinx.collectionsImmutable)

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.coreSplashScreen)

    implementation(platform(Compose.bom))
    implementation(Compose.animation)
    implementation(Compose.foundation)
    implementation(Compose.compiler)
    implementation(Compose.ui)
    debugImplementation(Compose.uiTooling)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.hiltNavigation)
    implementation(Compose.material3)
    implementation(Compose.runtime)
    implementation(Compose.navigation)
    implementation(Compose.viewModel)
    implementation(Compose.lifecycleRuntime)
    implementation(Compose.activity)
    implementation(Compose.coil)
    implementation(Compose.stateEvents)

    implementation(Accompanist.permissions)
    implementation(Accompanist.pager)
    implementation(Accompanist.pagerIndicators)

    implementation(DaggerHilt.hiltAndroid)
    kapt(DaggerHilt.hiltCompiler)

    kapt(Room.roomCompiler)
    implementation(Room.roomKtx)
    implementation(Room.roomRuntime)

    implementation(Ktor.clientCore)
    implementation(Ktor.clientAndroid)
    implementation(Ktor.clientContentNegotiation)
    implementation(Ktor.clientLogging)
    implementation(Ktor.serializationKotlinxJson)

    implementation(CyberAgent.gpuImage)

    testImplementation(Testing.junit4)
    testImplementation(Testing.junitAndroidExt)
    testImplementation(Testing.truth)
    testImplementation(Testing.coroutines)
    testImplementation(Testing.turbine)
    testImplementation(Testing.composeUiTest)
    testImplementation(Testing.mockk)
    testImplementation(Testing.mockWebServer)

    androidTestImplementation(Testing.junit4)
    androidTestImplementation(Testing.junitAndroidExt)
    androidTestImplementation(Testing.truth)
    androidTestImplementation(Testing.coroutines)
    androidTestImplementation(Testing.turbine)
    androidTestImplementation(Testing.composeUiTest)
    androidTestImplementation(Testing.mockkAndroid)
    androidTestImplementation(Testing.mockWebServer)
    androidTestImplementation(Testing.hiltTesting)
    kaptAndroidTest(DaggerHilt.hiltCompiler)
    androidTestImplementation(Testing.testRunner)
}