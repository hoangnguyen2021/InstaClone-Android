apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Module.core))

    "implementation"(Kotlinx.collectionsImmutable)
    "implementation"(DataStore.protobuf)
}