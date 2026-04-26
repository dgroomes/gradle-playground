val slf4jVersion = "2.0.17"

subprojects {
    apply(plugin = "java")
    apply(plugin = "application")

    repositories {
        mavenCentral()
    }

    dependencies {
        /*
        This is the sharpest example of the downsides of using 'subprojects' (and the like) for doing cross-project
        configuration. Here, the Gradle Kotlin DSL can't give us the generated type-safe accessors like 'implementation(...)'
        and instead we have to quote the "implementation" and "runtimeOnly" function calls and we lose IDE awareness on
        those call sites.

        This effect shows up in Gradle's own examples, like https://github.com/gradle/gradle/blob/5c4943976d9506211ba12abe693365f878d5d769/platforms/documentation/docs/src/snippets/reference/dsl-apis/noAccessors/kotlin/build.gradle.kts#L11-L20
        */
        "implementation"("org.slf4j:slf4j-api:$slf4jVersion")
        "runtimeOnly"("org.slf4j:slf4j-simple:$slf4jVersion")
    }
}

/**
 * We can configure a subproject from the root project by getting a handle to it with the `project` method and
 * executing the configuration in the closure. `JavaApplication` is a real extension type contributed by the
 * `application` plugin, so `configure<JavaApplication> { ... }` gives us a typed receiver. This is a useful contrast
 * with the quoted dependency configuration names above, which are only names and not extension types.
 */
val projectA = project(":project-a") {
    configure<JavaApplication> {
        mainClass.set("dgroomes.multi_project_cross_configuration.project_a.MainA")
    }
}
