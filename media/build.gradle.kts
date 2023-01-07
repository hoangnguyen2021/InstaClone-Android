apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Module.core))
    "implementation"(project(Module.coreUi))
}