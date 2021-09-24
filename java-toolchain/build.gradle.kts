plugins {
    java
    application
}

repositories {
    mavenCentral()
}

val junitJupiterVersion = "5.8.0" // releases: https://junit.org/junit5/docs/current/release-notes/index.html

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(18))
    }
}

tasks {
    withType(Test::class.java) {
        useJUnitPlatform()
    }
}

application {
    mainClass.set("dgroomes.App")
}
