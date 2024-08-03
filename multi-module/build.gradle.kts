val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

subprojects {
    apply(plugin = "java")
    apply(plugin = "application")

    repositories {
        mavenCentral()
    }

    dependencies {
        /*
        Am I doing this right? Putting "implementation" in quotes? Can't I omit the quotes and get the strong typing?
        Isn't strong typing and auto-complete the whole point of the Gradle Kotlin DSL versus the traditional
        Groovy-based build.gradle file? Well, according to the official examples, this is the right trick! https://github.com/gradle/gradle/blob/da613276316a21508a6e30e19d1923a312578b84/subprojects/docs/src/snippets/multiproject/dependencies-java/kotlin/build.gradle.kts#L9

        This is a little disappointing... but overall the Gradle Kotlin DSL is pretty cool and works pretty well in
        Intellij.

        UPDATE 2020-05-31 Yes it looks like there is an official word on this topic. The activity of configuring a
        subproject is defined as "cross-configuring" (https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:kotlin_cross_project_configuration)
        The note in that article says "Taking this approach means that you won’t be able to use type-safe accessors for
        model elements contributed by the plugins. You will instead have to rely on string literals and the standard
        Gradle APIs." So there you have it: cross-configuring is a significant trade-off that might make your
        project's build files more DRY/expressive but limits the ability of the Gradle Kotlin DSL to bring type-safety to
        those very same build files.

        UPDATE 2022-08-28 Continuing the type-unsafe trend... Now I want to use "version catalogs" and the associated
        TOML file. But when I try to use 'libs.slf4j.api' I'm getting the error: 'Extension with name 'libs' does not exist.'
        I need to use the version catalog with the type-unsafe API described here https://docs.gradle.org/current/userguide/platforms.html#sub:type-unsafe-access-to-catalog
        Specifically, I need: extensions.getByType<VersionCatalogsExtension>().named("libs")
        */
        "implementation"(versionCatalog.findLibrary("slf4j-api").get())
    }
}

/**
 * We can configure a subproject from the root project by getting a handle to it with the 'project' method and
 * executing the configuration in the closure. This is not idiomatic. Usually, the configuration specific to the
 * subproject would be defined in its own `build.gradle.kts` file, but this is an available option!
 */
val moduleA = project(":module-a") {
    configure<JavaApplication> {
        mainClass.set("dgroomes.multi_module.module_a.MainA")
    }
}

val moduleB = project(":module-b")

/**
 * Similar to the 'subprojects' block, the 'configure' method is a way we can apply configuration to multiple
 * subprojects.
 */
configure(listOf(moduleA, moduleB)) {
    dependencies {
        "runtimeOnly"(versionCatalog.findLibrary("slf4j-simple").get())
    }
}
