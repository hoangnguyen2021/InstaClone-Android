apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Module.core))
    "implementation"(project(Module.coreUi))
    "implementation"(project(Module.settings))

    "implementation"(Ktor.clientCore)
    "implementation"(Ktor.clientAndroid)
    "implementation"(Ktor.clientContentNegotiation)
    "implementation"(Ktor.clientLogging)
    "implementation"(Ktor.serializationKotlinxJson)
}