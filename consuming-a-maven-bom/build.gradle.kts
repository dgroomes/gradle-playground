plugins {
    java
}

repositories {
    mavenCentral()
}

// Jackson releases: https://github.com/FasterXML/jackson/wiki/Jackson-Releases
// Usually, we don't like to code to a dynamic version ("+" sign), but because we are using a lock file, we still have
// a complete understanding of the resolved dependencies. The lock file is version controlled and should be reviewed
// before committing changes.
val jacksonBomVersion = "2.19.+"

// Let's try to force a downgrade to a specific version of jackson-databind which is different from what is defined in the BOM
val jacksonDatabindVersion = "2.19.1"

dependencies {
    /*
    Importing a Maven BOM enables convenience and safety with managing transitive dependencies. In this case, it
    lets us omit the versions of Jackson dependency declarations declared later in this 'dependencies' block. That's
    nice! Also, the versions of Jackson dependencies that it automatically uses are a set of "known to work well
    together" versions. This is a useful safeguard that should reduce our chances of getting unwelcome problems like
    a NoClassDefFoundError exception at runtime.

    Read about Gradle's support for Maven BOMs at https://docs.gradle.org/current/userguide/platforms.html#sub:bom_import
    */
    implementation(platform("com.fasterxml.jackson:jackson-bom:$jacksonBomVersion"))

    /*
    Notice how we don't have to declare a version in these dependency declarations! This is because the earlier Maven
    BOM import already defines specific versions for each of these dependencies.
    */
    implementation("com.fasterxml.jackson.core:jackson-annotations")
    implementation("com.fasterxml.jackson.core:jackson-core")
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-csv")

    /*
    In this contrived case, we want to override the version of the 'jackson-databind' dependency that is defined in
    the Maven BOM. Instead of using this version, for whatever reason, we want to downgrade to a lower version of
    this dependency for compilation and runtime. We can accomplish that by using the "strictly" rule. Read about this
    feature at https://docs.gradle.org/current/userguide/rich_versions.html#sec:strict-version
    */
    implementation("com.fasterxml.jackson.core:jackson-databind") {
        version {
            strictly(jacksonDatabindVersion)
        }
    }

    /*
     Finally, when all is said and done, Gradle will resolve our dependencies to the following versions. This is the
     output of the command `./gradlew dependencies --configuration compileClasspath`:

     > Task :dependencies

     ------------------------------------------------------------
     Root project 'consuming-a-maven-bom'
     ------------------------------------------------------------

     compileClasspath - Compile classpath for source set 'main'.
     +--- com.fasterxml.jackson:jackson-bom:2.19.+ -> 2.19.2
     |    +--- com.fasterxml.jackson.core:jackson-annotations:2.19.2 (c)
     |    +--- com.fasterxml.jackson.core:jackson-core:2.19.2 (c)
     |    +--- com.fasterxml.jackson.core:jackson-databind:2.19.2 -> 2.19.1 (c)
     |    +--- com.fasterxml.jackson.dataformat:jackson-dataformat-csv:2.19.2 (c)
     |    \--- com.fasterxml.jackson.module:jackson-module-parameter-names:2.19.2 (c)
     +--- com.fasterxml.jackson.core:jackson-annotations -> 2.19.2
     |    \--- com.fasterxml.jackson:jackson-bom:2.19.2 (*)
     +--- com.fasterxml.jackson.core:jackson-core -> 2.19.2
     |    \--- com.fasterxml.jackson:jackson-bom:2.19.2 (*)
     +--- com.fasterxml.jackson.module:jackson-module-parameter-names -> 2.19.2
     |    +--- com.fasterxml.jackson.core:jackson-core:2.19.2 (*)
     |    +--- com.fasterxml.jackson.core:jackson-databind:2.19.2 -> 2.19.1
     |    |    +--- com.fasterxml.jackson.core:jackson-annotations:2.19.1 -> 2.19.2 (*)
     |    |    +--- com.fasterxml.jackson.core:jackson-core:2.19.1 -> 2.19.2 (*)
     |    |    \--- com.fasterxml.jackson:jackson-bom:2.19.1 -> 2.19.2 (*)
     |    \--- com.fasterxml.jackson:jackson-bom:2.19.2 (*)
     +--- com.fasterxml.jackson.dataformat:jackson-dataformat-csv -> 2.19.2
     |    +--- com.fasterxml.jackson.core:jackson-databind:2.19.2 -> 2.19.1 (*)
     |    +--- com.fasterxml.jackson.core:jackson-annotations:2.19.2 (*)
     |    +--- com.fasterxml.jackson.core:jackson-core:2.19.2 (*)
     |    \--- com.fasterxml.jackson:jackson-bom:2.19.2 (*)
     +--- com.fasterxml.jackson.core:jackson-databind:{strictly 2.19.1} -> 2.19.1 (*)
     +--- com.fasterxml.jackson:jackson-bom:{strictly 2.19.2} -> 2.19.2 (c)
     +--- com.fasterxml.jackson.module:jackson-module-parameter-names:{strictly 2.19.2} -> 2.19.2 (c)
     +--- com.fasterxml.jackson.core:jackson-core:{strictly 2.19.2} -> 2.19.2 (c)
     +--- com.fasterxml.jackson.core:jackson-databind:{strictly 2.19.1} -> 2.19.1 (c)
     +--- com.fasterxml.jackson.dataformat:jackson-dataformat-csv:{strictly 2.19.2} -> 2.19.2 (c)
     \--- com.fasterxml.jackson.core:jackson-annotations:{strictly 2.19.2} -> 2.19.2 (c)

     (c) - A dependency constraint, not a dependency. The dependency affected by the constraint occurs elsewhere in the tree.
     (*) - Indicates repeated occurrences of a transitive dependency subtree. Gradle expands transitive dependency subtrees only once per project; repeat occurrences only display the root of the subtree, followed by this annotation.
     */
}

/*
As a convenience, we can enable dependency locking so that we can look at the generated `gradle.lockfile` file to see
the resolved dependencies.
 */
dependencyLocking {
    lockAllConfigurations()
}
