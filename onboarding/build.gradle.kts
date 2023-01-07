apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Module.coreUi))

    "implementation"(Ktor.clientCore)
    "implementation"(Ktor.clientAndroid)
    "implementation"(Ktor.clientContentNegotiation)
    "implementation"(Ktor.clientLogging)
    "implementation"(Ktor.serializationKotlinxJson)

    "implementation"(Twilio.javaSdk)

    "implementation"(Canhub.androidImageCropper)
}