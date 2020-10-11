# java-preview-features-groovy-dsl

This project shows how to configure Gradle to build and run a project that uses Java [_Preview Features_](https://openjdk.java.net/jeps/12).

This project uses Gradle's original [*Groovy DSL*](https://docs.gradle.org/current/dsl/index.html). For comparison with
the Gradle Kotlin DSL, see the sibling project `java-preview-features/`.

---

This repo in particular showcases the new Java 15 _Records_ language feature which is a preview feature hidden behind
the `--enable-preview` Java flag.

You will need to use [Intellij IDEA 2020.2 or later to get Java 15 support](https://blog.jetbrains.com/idea/2020/09/java-15-and-intellij-idea/).

### Instructions

* Use Java 15
* Build and run the application with `./gradlew run`. It should output the following:
    ```
    > Task :compileJava
    Note: /Users/davidgroomes/repos/personal/gradle-playground/java-preview-features/src/main/java/dgroomes/MyMessage.java uses preview language features.
    Note: Recompile with -Xlint:preview for details.
    
    > Task :run
    MyMessage[message=Hello world!]
    
    BUILD SUCCESSFUL in 6s
    2 actionable tasks: 2 executed
    ```
    Notice the warning:
      > MyMessage.java uses preview language features.
    There is no way to suppress this because Java's preview features are designed to allow breaking changes in future
    releases, so the compile-time reminder is a welcome one.   
  * Run the tests with `./gradlew test`
