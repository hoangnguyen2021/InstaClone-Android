apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(Compose.uiUtil)
    "implementation"(Accompanist.systemUiController)
}