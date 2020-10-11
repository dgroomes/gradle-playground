plugins {
    java
    application
}

repositories {
    jcenter()
    mavenLocal()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")
}

java {
    sourceCompatibility = JavaVersion.VERSION_15
    targetCompatibility = JavaVersion.VERSION_15
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
    mainClassName = "dgroomes.App"
}
