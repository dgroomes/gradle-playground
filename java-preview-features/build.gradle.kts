plugins {
    java
    application
}

repositories {
    mavenCentral()
}

val junitVersion = "5.13.4" // releases: https://junit.org/junit5/docs/current/release-notes/index.html

dependencies {
    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
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
