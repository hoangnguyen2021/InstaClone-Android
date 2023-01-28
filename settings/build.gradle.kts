apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(DataStore.protobuf)
    "implementation"(Kotlinx.collectionsImmutable)
}