# build-source

This sub-project showcases [Gradle's `buildSrc` feature](https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#sec:build_sources).

Specifically this sub-project defines a custom task to help execute a `jshell` session loaded with the dependencies
declared in the Gradle project.

## Instructions

* Use Java 15
* Build the classpath file with `./gradlew printClassPath`
* Start a `jshell` session with `./run.sh`
* Explore! Enter something like:
    ```
    var log = org.slf4j.LoggerFactory.getLogger("MyLogger");
    log.info("Hello {}!", "world");
    ```
* Altogether, it will look something like this:
    ```
    ./gradlew clean printClassPath && ./run.sh

    > Task :printClassPath
    Wrote runtime classpath to /Users/davidgroomes/repos/personal/gradle-playground/build-source/build/runtime-classpath.txt
    
    BUILD SUCCESSFUL in 3s
    2 actionable tasks: 2 executed
    |  Welcome to JShell -- Version 15.0.1
    |  For an introduction type: /help intro
    
    jshell> var log = org.slf4j.LoggerFactory.getLogger("MyLogger");
    ...> log.info("Hello {}!", "world");
    log ==> org.slf4j.impl.SimpleLogger(MyLogger)
    |  created variable log : org.slf4j.Logger
    [main] INFO MyLogger - Hello world!
    ```

## WishList

General clean-ups, TODOs and things I wish to implement for this project:

* Make the Gradle `buildSrc` content more idiomatic. It should probably be a plugin.
