plugins {
    java
    application
}

repositories {
    mavenCentral()
}

val junitVersion = "6.0.3" // JUnit releases: https://junit.org/junit5/docs/current/release-notes/index.html

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(27))
    }
}

tasks {
    test {
        useJUnitPlatform()
    }
}

application {
    mainClass.set("dgroomes.java_early_access_via_toolchain.App")
}
