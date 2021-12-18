plugins {
    id("common")
    `java-library`
}

val jacksonVersion = "2.12.5" // Jackson releases: https://github.com/FasterXML/jackson/wiki/Jackson-Releases

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
}
