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
val jacksonBomVersion = "3.1.+"

// Let's try to force a downgrade to a specific version of jackson-databind which is different from what is defined in
// the BOM.
val jacksonDatabindVersion = "3.1.1"

dependencies {
    /*
    Importing a Maven BOM enables convenience and safety with managing transitive dependencies. In this case, it
    lets us omit the versions of Jackson dependency declarations declared later in this 'dependencies' block. That's
    nice! Also, the versions of Jackson dependencies that it automatically uses are a set of "known to work well
    together" versions. This is a useful safeguard that should reduce our chances of getting unwelcome problems like
    a NoClassDefFoundError exception at runtime.

    Read about Gradle's support for Maven BOMs at https://docs.gradle.org/current/userguide/platforms.html#sub:bom_import
    */
    implementation(platform("tools.jackson:jackson-bom:$jacksonBomVersion"))

    /*
    Notice how we don't have to declare a version in these dependency declarations! This is because the earlier Maven
    BOM import already defines specific versions for each of these dependencies.
    */
    implementation("tools.jackson.core:jackson-core")
    implementation("tools.jackson.dataformat:jackson-dataformat-csv")

    // Note: in the 3.x line, Jackson decided to keep the `jackson-annotations` module in the 2.x world: keep the old
    // group ID, old Java package name, and old 2.x version numbers. This was done for compability reasons.
    implementation("com.fasterxml.jackson.core:jackson-annotations")

    /*
    In this contrived case, we want to override the version of the 'jackson-databind' dependency that is defined in
    the Maven BOM. Instead of using this version, for whatever reason, we want to downgrade to a lower version of
    this dependency for compilation and runtime. We can accomplish that by using the "strictly" rule. Read about this
    feature at https://docs.gradle.org/current/userguide/dependency_versions.html#sec:rich-version-constraints
    */
    implementation("tools.jackson.core:jackson-databind") {
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
     +--- tools.jackson:jackson-bom:3.1.+ -> 3.1.2
     |    +--- com.fasterxml.jackson.core:jackson-annotations:2.21 (c)
     |    +--- tools.jackson.core:jackson-core:3.1.2 (c)
     |    +--- tools.jackson.core:jackson-databind:3.1.2 -> 3.1.1 (c)
     |    \--- tools.jackson.dataformat:jackson-dataformat-csv:3.1.2 (c)
     +--- com.fasterxml.jackson.core:jackson-annotations -> 2.21
     +--- tools.jackson.core:jackson-core -> 3.1.2
     |    \--- tools.jackson:jackson-bom:3.1.2 (*)
     +--- tools.jackson.dataformat:jackson-dataformat-csv -> 3.1.2
     |    +--- tools.jackson.core:jackson-databind:3.1.2 -> 3.1.1
     |    |    +--- com.fasterxml.jackson.core:jackson-annotations:2.21
     |    |    +--- tools.jackson.core:jackson-core:3.1.1 -> 3.1.2 (*)
     |    |    \--- tools.jackson:jackson-bom:3.1.1 -> 3.1.2 (*)
     |    +--- com.fasterxml.jackson.core:jackson-annotations:2.21
     |    +--- tools.jackson.core:jackson-core:3.1.2 (*)
     |    \--- tools.jackson:jackson-bom:3.1.2 (*)
     +--- tools.jackson.core:jackson-databind:{strictly 3.1.1} -> 3.1.1 (*)
     +--- tools.jackson.core:jackson-databind:{strictly 3.1.1} -> 3.1.1 (c)
     +--- tools.jackson.core:jackson-core:{strictly 3.1.2} -> 3.1.2 (c)
     +--- tools.jackson:jackson-bom:{strictly 3.1.2} -> 3.1.2 (c)
     +--- com.fasterxml.jackson.core:jackson-annotations:{strictly 2.21} -> 2.21 (c)
     \--- tools.jackson.dataformat:jackson-dataformat-csv:{strictly 3.1.2} -> 3.1.2 (c)

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
