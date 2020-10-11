# java-early-access

This sub-project illustrates how to use early access versions of Java in a Gradle project by leveraging Gradle's forking
and "executable" configuration options.

---

Gradle itself cannot always run on new versions or work-in-progress versions of Java. These versions of Java are
sometimes called _Early Access_ versions. For example, in October 2020 the early access version of Java is Java 16 which
is due out for release next year in March 2021.

> Friendly reminder: use the official OpenJDK site to stay up-to-date on the latest OpenJDK plans, like [Java 16](https://openjdk.java.net/projects/jdk/16/spec/).

Fortunately, Gradle does support compiling Java source for a so-called _Early Access_ version of Java, like Java 16,
even if Gradle itself cannot run on that version of Java! Gradle can do this because it can _fork_ a process to execute
the `compileJava` task with **a different JDK than the JDK used to execute Gradle itself**. Similarly, Gradle can be
configured to use alternative JDKs to execute *execution* tasks like `run` and `test` (the `run` task is provided by the
[application plugin](https://docs.gradle.org/current/userguide/application_plugin.html)).

The official Gradle docs have guidance on [_Targeting a specific Java version_](https://docs.gradle.org/6.6.1/userguide/building_java_projects.html#sec:java_cross_compilation)
that's different from the version of Java used to run Gradle.

**FUTURE-LOOKING**: In the upcoming release of Gradle 6.7 there is a built-in feature to accommodate this exact
requirement: [Toolchain support for JVM projects](https://docs.gradle.org/6.7-rc-4/release-notes.html#jvm-toolchains).
Stay on the lookout for that!     

### Instructions

1. Use Java 11 or 15
1. Set the environment variable `JAVA_16_HOME` to the path of a JDK 16 installation on your computer
1. Run the program with `./gradlew run`
1. Run the tests with `./gradlew test`

### Notes

If you are using an early access version of Java then you will almost definitely be curious to use preview language
features. For a working example of how to configure Gradle to enable Java language preview features, see the
`java-preview-features/` sibling project.
