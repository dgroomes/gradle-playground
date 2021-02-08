plugins {
    java
}

repositories {
    mavenCentral()
}

val jacksonBomVersion = "2.11.2" // releases: https://github.com/FasterXML/jackson/wiki/Jackson-Releases
val jacksonDatabindVersion = "2.11.1" // let's try to force a downgrade to a specific version of jackson-databind which is different from what is defined in the BOM

dependencies {
    /**
     * Importing a Maven BOM enables convenience and safety with managing transitive dependencies. In this case, it
     * lets us omit the versions of Jackson dependency declarations declared later in this 'dependencies' block. That's
     * nice! Also, the versions of Jackson dependencies that it automatically uses are a set of "known to work well
     * together" versions. This is a useful safeguard that should reduce our chances of getting an unwelcome NoClassDefFoundError
     * at runtime.
     *
     * Read about Gradle's support for Maven BOMs at https://docs.gradle.org/current/userguide/platforms.html#sub:bom_import
     */
    implementation(platform("com.fasterxml.jackson:jackson-bom:$jacksonBomVersion"))

    /**
     * Notice how we don't have to declare a version in these dependency declarations! This is because the earlier Maven
     * BOM import already defines specific versions for each of these dependencies.
     */
    implementation("com.fasterxml.jackson.core:jackson-annotations")
    implementation("com.fasterxml.jackson.core:jackson-core")
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-csv")

    /**
     * In this contrived case, we want to override the version of the 'jackson-databind' dependency that is defined in
     * the Maven BOM. Instead of using this version, for whatever reason, we want to downgrade to a lower version of
     * this dependency for compilation and runtime. We can accomplish that by using the "strictly" rule. Read about this
     * feature at https://docs.gradle.org/current/userguide/rich_versions.html#sec:strict-version
     */
    implementation("com.fasterxml.jackson.core:jackson-databind") {
        version {
            strictly(jacksonDatabindVersion)
        }
    }

    /**
     * Finally, when all is said and done, Gradle will resolve our dependencies to the following versions. This is the
     * output of the command `./gradlew dependencies --configuration compileClasspath`:
     *
        > Task :dependencies

        ------------------------------------------------------------
        Root project
        ------------------------------------------------------------

        compileClasspath - Compile classpath for source set 'main'.
        +--- com.fasterxml.jackson:jackson-bom:2.11.2
        |    +--- com.fasterxml.jackson.core:jackson-annotations:2.11.2 (c)
        |    +--- com.fasterxml.jackson.core:jackson-core:2.11.2 (c)
        |    +--- com.fasterxml.jackson.core:jackson-databind:2.11.2 -> 2.11.1 (c)
        |    +--- com.fasterxml.jackson.dataformat:jackson-dataformat-csv:2.11.2 (c)
        |    \--- com.fasterxml.jackson.module:jackson-module-parameter-names:2.11.2 (c)
        +--- com.fasterxml.jackson.core:jackson-annotations -> 2.11.2
        +--- com.fasterxml.jackson.core:jackson-core -> 2.11.2
        +--- com.fasterxml.jackson.module:jackson-module-parameter-names -> 2.11.2
        |    +--- com.fasterxml.jackson.core:jackson-core:2.11.2
        |    \--- com.fasterxml.jackson.core:jackson-databind:2.11.2 -> 2.11.1
        |         +--- com.fasterxml.jackson.core:jackson-annotations:2.11.1 -> 2.11.2
        |         \--- com.fasterxml.jackson.core:jackson-core:2.11.1 -> 2.11.2
        +--- com.fasterxml.jackson.dataformat:jackson-dataformat-csv -> 2.11.2
        |    +--- com.fasterxml.jackson.core:jackson-databind:2.11.2 -> 2.11.1 (*)
        |    +--- com.fasterxml.jackson.core:jackson-annotations:2.11.2
        |    \--- com.fasterxml.jackson.core:jackson-core:2.11.2
        \--- com.fasterxml.jackson.core:jackson-databind:{strictly 2.11.1} -> 2.11.1 (*)

        (c) - dependency constraint
        (*) - dependencies omitted (listed previously)
     */
}
