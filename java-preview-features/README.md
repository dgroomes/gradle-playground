# java-preview-features

This project shows how to configure Gradle to build and run a project that uses Java [_Preview Features_](https://openjdk.java.net/jeps/12).

This project uses Gradle's [*Kotlin DSL*](https://docs.gradle.org/current/userguide/kotlin_dsl.html). For comparison
with the Gradle Groovy DSL, see the sibling project `java-preview-features-groovy-dsl/`.

---

This repo in particular showcases the new Java 16 _Sealed Classes_ language feature which is a preview feature hidden behind
the `--enable-preview` Java flag.

You will need to use [Intellij IDEA 2021.1 or later to get Java 16 support](https://blog.jetbrains.com/idea/2021/03/java-16-and-intellij-idea/).

### Instructions

* Use Java 16
* Build and run the application with `./gradlew run`. It should output the following:
    ```
    ./gradlew run
    
    > Task :compileJava
    Note: Some input files use preview language features.
    Note: Recompile with -Xlint:preview for details.
    
    > Task :run
    It's Earth, an instance of Planet. Its atmosphere is: mostly nitrogen.
    It's Earth, an instance of Planet. Its atmosphere is: mostly nitrogen.
    It's Mars, an instance of Planet. Its atmosphere is: carbon dioxide
    It's Earth, an instance of Planet. Its atmosphere is: mostly nitrogen.
    It's Mars, an instance of Planet. Its atmosphere is: carbon dioxide
    
    BUILD SUCCESSFUL in 1s
    2 actionable tasks: 2 executed
    ```
    Notice the warning:
      > Note: Some input files use preview language features.
    There is no way to suppress this because Java's preview features are designed to allow breaking changes in future
    releases, so the compile-time reminder is a welcome one.   
  * Run the tests with `./gradlew test`
