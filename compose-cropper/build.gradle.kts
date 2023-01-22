apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"("com.github.SmartToolFactory:Compose-Extended-Gestures:2.1.0")
    "implementation"("androidx.compose.material:material-icons-extended:1.3.1")
}