# java-toolchain

This sub-project illustrates how to leverage Gradle's [Toolchains for JVM projects](https://docs.gradle.org/current/userguide/toolchains.html)
to use an early-access version of Java in a Gradle project.

## What?

This is a Java 18 _Early Access_ project that's built with Java 17. Wait, what does that mean? This is a bit of a facetious
statement. There's no trickery here. Remember that Gradle itself is a Java program and we are free to use a different version
of Java to run Gradle than the version of Java used to compile and run our program. In this project:

* The Gradle process runs on Java 17
    * Gradle is our build tool. As such, we can facetiously say "we use Java 17 to build the project".
* The compilation process runs on Java 18
    * Specifically, the `./gradlew compileJava` task will kick off a Java 18 `javac` process. Gradle refers to this pattern
      of creating a secondary Java process as "forking a Java process".
* The testing process runs on Java 18
    * Specifically, the `./gradlew test` task will fork a Java 18 process to run the JUnit tests.
* The run process uses Java 18
    * Specifically, the `./gradlew run` task will fork a Java 18 process to run the program (The `public static void main(String... args)`
      method)
    * The `run` task is provided by the [application plugin](https://docs.gradle.org/current/userguide/application_plugin.html))

## Why?

Out of convenience and simplicity, its best to use the same version of Java to run Gradle and to compile, test and run your
program. So, why ever use two different versions? Out of necessity. Gradle itself cannot always run on new versions or
work-in-progress versions of Java. These versions of Java are called _Early Access_ versions. For example, in October 2020
the early access version of Java was Java 16 and the latest version of Gradle was Gradle 6.x. Gradle 6.x fails on Java 16.
So, at the time it was necessary to have a split-version project.

> Friendly reminder: use the official OpenJDK site to stay up-to-date on the latest OpenJDK plans, like [Java 18](https://openjdk.java.net/projects/jdk/18/spec/).

The Gradle mechanic to configure a split-version project is Gradle's [Toolchains for JVM projects](https://docs.gradle.org/current/userguide/toolchains.html).

## Instructions

1. Use Java 17
2. Make sure that Java 18 is installed in a location known to Gradle
    * Gradle can [auto-detect installations of Java and the JDK](https://docs.gradle.org/current/userguide/toolchains.html#sec:auto_detection)
    * Alternatively, you can [specify a custom location](https://docs.gradle.org/current/userguide/toolchains.html#sec:custom_loc)
3. Run the program:
    * `./gradlew run`
4. Run the tests:
    * `./gradlew test`

## Notes

If you are using an early access version of Java then you will almost definitely be curious to use preview language
features. For a working example of how to configure Gradle to enable Java language preview features, see the
`java-preview-features/` sibling project.


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* Rename this to "java-early-access". I am generally favoring using toolchains everywhere now. So having a standalone
  example project dedicated to toolchains is a bit redundant. This project is actually a great example of just "How do I
  set up a Gradle project to work on an early access version of Java?". I think `java-early-access` is a better way to
  position this project than `java-toolchain`.
