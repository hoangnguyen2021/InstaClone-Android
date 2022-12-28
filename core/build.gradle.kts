apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(Google.libphonenumber)
    "implementation"(Jmail.jmail)
}