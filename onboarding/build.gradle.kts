apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Module.coreUi))
}