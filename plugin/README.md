# plugin

This sub-project showcases a custom Gradle plugin and [Gradle's `buildSrc` feature](https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#sec:build_sources).

Specifically this sub-project defines a custom Gradle plugin that defines a task to list the full path to each runtime
dependency of the project.

The task prints the content to a file and separates each path by the colon character (':') so that the file's contents can
be used conveniently as the classpath argument to the `java` command. The availability of this classpath file is a kind
of "escape hatch" from Gradle so that we can use hand-written `java` commands to execute our program or we can use `jshell`
with libraries! This project showcases both.

## Instructions

* Use Java 15
* Compile the program's source code with `./gradlew compileJava`
  * Notice the class files in `build/classes/java/main/`
* Build the classpath file with `./gradlew listDependencies`
  * Notice the file `build/runtime-dependencies.txt` 
* Run the program with `./run.sh`. You should see something like this:
    ```
    [main] INFO dgroomes.Main - Hello world from the 'main' method!
    ```
* Alternatively to running the program, start a `jshell` session with `./jshell.sh`
* Explore! Enter something like:
    ```
    var log = org.slf4j.LoggerFactory.getLogger("MyLogger");
    log.info("Hello world from {}!", "jshell");
    ```
* Altogether, the `jshell` flow looks something like this:
    ```
    ./gradlew clean listDependencies && ./jshell.sh
    
    > Task :listDependencies
    Wrote runtime classpath to /Users/davidgroomes/repos/personal/gradle-playground/plugin/build/runtime-dependencies.txt
    
    BUILD SUCCESSFUL in 900ms
    2 actionable tasks: 2 executed
    |  Welcome to JShell -- Version 15.0.1
    |  For an introduction type: /help intro
    
    jshell>     var log = org.slf4j.LoggerFactory.getLogger("MyLogger");
    ...>     log.info("Hello world from {}!", "jshell");
    log ==> org.slf4j.impl.SimpleLogger(MyLogger)
    |  created variable log : org.slf4j.Logger
    [main] INFO MyLogger - Hello world from jshell!
    
    jshell>
    ```

## WishList

General clean-ups, TODOs and things I wish to implement for this project:

* IN PROGRESS Make the Gradle `buildSrc` content more idiomatic. It should probably be a plugin.
  * DONE make it a plugin
  * Make the task depend on `build` for convenience?
  * DONE Use the "plugins" block to apply the plugin
  * SKIPPED (there's no config necessary for this plugin so it doesn't make sense) Use the proper file APIs. See [*Working with files in custom tasks and plugins*](https://docs.gradle.org/current/userguide/custom_plugins.html#sec:working_with_files_in_custom_tasks_and_plugins)
  * DONE Identify tasks inputs and outputs whihc enables Gradle's Incremental Build capability.

## Referenced material

* [Gradle docs: *Developing Custom Gradle Plugins*](https://docs.gradle.org/current/userguide/custom_plugins.html)
* [Gradle docs: *Authoring Tasks*](https://docs.gradle.org/current/userguide/more_about_tasks.html)
  * [*Task outcomes*](https://docs.gradle.org/current/userguide/more_about_tasks.html#sec:task_outcomes)
  * [*Up-to-date checks (AKA Incremental Builds)*](https://docs.gradle.org/current/userguide/more_about_tasks.html#sec:up_to_date_checks)
