apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Module.core))

    "implementation"(DataStore.protobuf)
}