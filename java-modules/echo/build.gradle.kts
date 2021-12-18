plugins {
    `java-library`
}

val jacksonVersion = "2.12.5" // Jackson releases: https://github.com/FasterXML/jackson/wiki/Jackson-Releases

java {
    modularity.inferModulePath.set(true)
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
}
