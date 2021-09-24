plugins {
    java
    id("dgroomes.dependencies-lister")
}

repositories {
    mavenCentral()
}

val slf4jVersion = "1.7.32" // releases: http://www.slf4j.org/news.html

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")
}
