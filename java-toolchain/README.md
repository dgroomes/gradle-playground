# java-toolchain

This sub-project illustrates how to leverage Gradle's [Toolchains for JVM projects](https://docs.gradle.org/current/userguide/toolchains.html)
to use an early-access version of Java in a Gradle project.

---

Gradle itself cannot always run on new versions or work-in-progress versions of Java. These versions of Java are
sometimes called _Early Access_ versions. For example, in October 2020 the early access version of Java was Java 16 which
was not compatible with Gradle 6.x which was the latest version of Gradle available at the time.

> Friendly reminder: use the official OpenJDK site to stay up-to-date on the latest OpenJDK plans, like [Java 16](https://openjdk.java.net/projects/jdk/16/spec/).

Fortunately, Gradle does support compiling Java source for a so-called _Early Access_ version of Java, like Java 16,
even if Gradle itself cannot run on that version of Java! Gradle can do this because it can _fork_ a process to execute
the `compileJava` task with **a different JDK than the JDK used to execute Gradle itself**. Similarly, Gradle can be
configured to use alternative JDKs to execute *execution* tasks like `run` and `test` (the `run` task is provided by the
[application plugin](https://docs.gradle.org/current/userguide/application_plugin.html)).

The official Gradle solution to this problem is to use Gradle's [Toolchains for JVM projects](https://docs.gradle.org/current/userguide/toolchains.html).

### Instructions

1. Use Java 11 or 16
1. Make sure that Java 17 is installed in a location known to Gradle
  * Gradle can [auto-detect installations of Java and the JDK](https://docs.gradle.org/current/userguide/toolchains.html#sec:auto_detection)
  * Alternatively, you can [specify a custom location](https://docs.gradle.org/current/userguide/toolchains.html#sec:custom_loc)  
1. Run the program with `./gradlew run`
1. Run the tests with `./gradlew test`

### Notes

If you are using an early access version of Java then you will almost definitely be curious to use preview language
features. For a working example of how to configure Gradle to enable Java language preview features, see the
`java-preview-features/` sibling project.
