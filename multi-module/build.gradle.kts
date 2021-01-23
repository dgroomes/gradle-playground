val slf4jVersion = "1.7.30" // releases: http://www.slf4j.org/news.html

subprojects {
    apply(plugin = "java")
    apply(plugin = "application")
    repositories {
        jcenter()
    }

    dependencies {
        /*
        Am I doing this right? Putting "implementation" in quotes? Can't I omit the quotes and get the strong typing?
        Isn't strong typing and auto-complete the whole point of the Gradle Kotlin DSL versus the traditional
        Groovy-based build.gradle file? Well, according to the official examples, this is the right trick! https://github.com/gradle/gradle/blob/da613276316a21508a6e30e19d1923a312578b84/subprojects/docs/src/snippets/multiproject/dependencies-java/kotlin/build.gradle.kts#L9

        This is a little disappointing... but overall the Gradle Kotlin DSL is pretty cool and works pretty well in
        Intellij.

        UPDATE 2020-05-31 Yes it looks like there is an official word on this topic. The activity of configuring a
        sub-project is defined as "cross-configuring" (https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:kotlin_cross_project_configuration)
        The note in that article says "Taking this approach means that you won’t be able to use type-safe accessors for
        model elements contributed by the plugins. You will instead have to rely on string literals and the standard
        Gradle APIs." So there you have it: cross-configuring is a significant trade-off that might make your
        project's build files more DRY/expressive but limits the ability of the Gradle Kotlin DSL to bring type-safety to
        those very same build files.
        */
        "implementation"("org.slf4j:slf4j-api:$slf4jVersion")
    }
}

/**
 * We can configure a sub-project from the root project by getting a handle to it with the 'project' method and
 * executing the configuration in the closure. This is not idiomatic. Usually, the configuration specific to the
 * sub-project would be defined in its own `build.gradle.kts` file, but this is an available option!
 */
val moduleA = project(":module-a") {
    configure<ApplicationPluginConvention> {
        mainClassName = "dgroomes.MainA"
    }
}

val moduleB = project(":module-b")

/**
 * Similar to the 'subprojects' block, the 'configure' method is a way we can apply configuration to multiple
 * sub-projects.
 */
configure(listOf(moduleA, moduleB)) {
    dependencies {
        "implementation"("org.slf4j:slf4j-simple:$slf4jVersion")
    }
}
