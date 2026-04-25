# java-preview-features

Configure Gradle to build and run a project that uses Java [_Preview Features_](https://openjdk.java.net/jeps/12).


## Overview

This repo in particular showcases the "primitive types in switch" preview language feature defined in [JEP 507](https://openjdk.org/jeps/507) in Java 25. Preview features are hidden behind the `--enable-preview` Java flag.

Please note: Intellij won't always support preview features!


## Instructions

Follow these instructions to build and run the demo program.

1. Pre-requisite: Java 25
2. Build and run the program:
   - ```shell
     ./gradlew run
     ```
   - It should output the following.
   - ```text
     > Task :compileJava
     Note: /Users/me/repos/personal/gradle-playground/java-preview-features/src/main/java/dgroomes/java_preview_features/App.java uses preview features of Java SE 25.
     Note: Recompile with -Xlint:preview for details.
     
     > Task :run
     Hello World! I am running in Java 25
     '1' is a little number
     ```
     Notice the warning:
       > Note: /...omitted.../App.java uses preview features of Java SE 25.
     
     There is no way to suppress this because Java's preview features are designed to allow breaking changes in future
     releases, so the compile-time reminder is a welcome one.   
3. Run the tests:
   - ```shell
     ./gradlew test
     ```


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

- [x] DONE (I could still use the planet types very effectively thanks to "pattern matching for switch") Upgrade to Java 20 and use a Java 20 preview feature. This will require throwing away the planet design for something
  else.
