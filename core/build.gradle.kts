apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(Google.libphonenumber)
    "implementation"(Jmail.jmail)
    "implementation"(Passay.passay)

    "implementation"(Ktor.clientCore)
    "implementation"(Ktor.clientAndroid)
    "implementation"(Ktor.clientContentNegotiation)
    "implementation"(Ktor.clientLogging)
    "implementation"(Ktor.serializationKotlinxJson)
}