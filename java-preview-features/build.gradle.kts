plugins {
    java
    application
}

repositories {
    mavenCentral()
}

val junitVersion = "5.10.3" // releases: https://junit.org/junit5/docs/current/release-notes/index.html

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

/**
 * Configure the compiler task, test task, start script creation task, and the run task to enable Java language "Preview
 * Features"
 */
tasks {
    withType(JavaCompile::class.java) {
        options.compilerArgs.addAll(arrayOf("--enable-preview"))
    }

    withType(Test::class.java) {
        jvmArgs = listOf("--enable-preview")
        useJUnitPlatform()
    }

    named<CreateStartScripts>("startScripts") {
        defaultJvmOpts = listOf("--enable-preview")
    }

    named<JavaExec>("run") {
        jvmArgs = listOf("--enable-preview")
    }
}

application {
    mainClass.set("dgroomes.java_preview_features.App")
}
