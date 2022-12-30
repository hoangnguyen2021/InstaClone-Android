apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Module.coreUi))

    "implementation"(Kotlin.kotlinxSerialization)
    "implementation"(Kotlin.kotlinxDateTime)

    "implementation"(Ktor.clientCore)
    "implementation"(Ktor.clientAndroid)
    "implementation"(Ktor.clientContentNegotiation)
    "implementation"(Ktor.clientLogging)
    "implementation"(Ktor.serializationKotlinxJson)

    "implementation"(Twilio.javaSdk)

    "implementation"(Canhub.androidImageCropper)
}