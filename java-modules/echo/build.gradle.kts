plugins {
    `java-library`
}

val jacksonVersion = "2.12.1" // releases: https://github.com/FasterXML/jackson/wiki/Jackson-Releases

java {
    modularity.inferModulePath.set(true)
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
}
