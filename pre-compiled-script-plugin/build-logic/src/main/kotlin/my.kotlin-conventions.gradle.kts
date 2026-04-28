// This pre-compiled script plugin applies common/conventional base setup for a Kotlin project with tests.

import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    id("org.jetbrains.kotlin.jvm")
}

repositories {
    mavenCentral()
}

// This is part of "Vampire's workaround". See the related detailed note in build.gradle.kts.
val libs = the<LibrariesForLibs>()

dependencies {
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks {
    test {
        useJUnitPlatform()
    }
}
