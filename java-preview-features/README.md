# java-preview-features

This project shows how to configure Gradle to build and run a project that uses Java [_Preview Features_](https://openjdk.java.net/jeps/12).

---

This repo in particular showcases the [Pattern Matching for `switch`](https://openjdk.org/jeps/433) preview language
feature in Java 20. Preview features are hidden behind the `--enable-preview` Java flag.

Please note: Intellij won't always support preview features!


## Instructions

Follow these instructions to build and run the demo program.

1. Use Java 17
2. Make sure that Java 20 is installed in a location known to Gradle
3. Build and run the program:
   * ```shell
     ./gradlew run
     ```
   * It should output the following.
     ```text
     > Task :compileJava
     Note: /Users/dave/repos/personal/gradle-playground/java-preview-features/src/main/java/dgroomes/App.java uses preview features of Java SE 20.
     Note: Recompile with -Xlint:preview for details.
     
     > Task :run
     It's Earth, an instance of Planet. Its atmosphere is: mostly nitrogen.
     It's Earth, an instance of Planet. Its atmosphere is: mostly nitrogen.
     It's Mars, an instance of Planet. Its atmosphere is: carbon dioxide
     It's an instance of Star. It says: I shine bright!
     It's an instance of Star. It says: I shine bright!
     ```
     Notice the warning:
       > Note: /...omitted.../App.java uses preview features of Java SE 20.
     
     There is no way to suppress this because Java's preview features are designed to allow breaking changes in future
     releases, so the compile-time reminder is a welcome one.   
4. Run the tests:
   * ```shell
     ./gradlew test
     ```


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* [x] DONE (I could still use the planet types very effectively thanks to "pattern matching for switch") Upgrade to Java 20 and use a Java 20 preview feature. This will require throwing away the planet design for something
  else.
