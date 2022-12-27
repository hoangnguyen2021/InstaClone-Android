object Ktor {
    private const val version = "2.2.1"

    val clientCore = "io.ktor:ktor-client-core:$version"
    val clientCio = "io.ktor:ktor-client-cio:$version"
    val clientAndroid = "io.ktor:ktor-client-android:$version"
    val clientContentNegotiation = "io.ktor:ktor-client-content-negotiation:$version"
    val clientLogging = "io.ktor:ktor-client-logging:$version"
    val serializationKotlinxJson = "io.ktor:ktor-serialization-kotlinx-json:$version"

    val logbackClassic = "ch.qos.logback:logback-classic:1.4.5"
}