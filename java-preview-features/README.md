# java-preview-features

This project shows how to configure Gradle to build and run a project that uses Java [_Preview Features_](https://openjdk.java.net/jeps/12).

---

This repo in particular showcases the new Java 16 _Sealed Classes_ language feature which is a preview feature hidden behind
the `--enable-preview` Java flag.

You will need to use [Intellij IDEA 2021.1 or later to get Java 16 support](https://blog.jetbrains.com/idea/2021/03/java-16-and-intellij-idea/).

### Instructions

1. Use Java 16
2. Build and run the application:
   * `./gradlew run`
   * It should output the following.
     ```bash
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
4. Run the tests:
   * `./gradlew test`

## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* Upgrade to Java 17 and use a Java 17 preview feature. This will require throwing away the planet design for something
  else.
