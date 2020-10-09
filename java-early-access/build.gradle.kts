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

project.getLogger().quiet("Configuring to compile with Java 16 and execute with Java 16")

val java16Home = System.getenv("JAVA_16_HOME")
if (java16Home == null || java16Home.isBlank()) {
    throw IllegalArgumentException("JAVA_16_HOME environment variable must be set to the path of JDK16 but was not set")
}
val java16HomeFile = File(java16Home)
if (!java16HomeFile.isDirectory()) {
    throw IllegalArgumentException("JAVA_16_HOME environment variable must be set to the path of JDK16 but was not a real directory: $java16Home")
}

java {
    /**
     * This is how to configure the Java compilation task ("compileJava") to use Java 16.
     */
    sourceCompatibility = JavaVersion.VERSION_16
    targetCompatibility = JavaVersion.VERSION_16
}

/**
 * Configure the compiler step to accommodate Java 16 by forking a javac process using JDK 16 instead of the Java
 * that is running the Gradle process. Similarly configure the "execution" tasks like "test" and "run" to use Java 16
 * as their execution JVM.
 */
tasks {
    withType(JavaCompile::class.java) {
        options.fork("javaHome" to java16HomeFile)
    }

    withType(Test::class.java) {
        setExecutable("$java16Home/bin/java")
        useJUnitPlatform()
    }

    named<CreateStartScripts>("startScripts") {
        // TODO how do I configure the script creation task to configure the start script to use Java 16?
    }

    named<JavaExec>("run") {
        setExecutable("$java16Home/bin/java")
    }
}

application {
    mainClassName = "dgroomes.App"
}
