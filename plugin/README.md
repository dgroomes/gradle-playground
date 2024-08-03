# plugin

Write and apply a custom Gradle plugin. This project also showcases [Gradle's `buildSrc` feature](https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#sec:build_sources).


## Overview 

Specifically this subproject defines a custom Gradle plugin that defines a task to list the full path to each runtime
dependency of the project.

The task prints the content to a file and separates each path by the colon character (':') so that the file's contents can
be used conveniently as the classpath argument to the `java` command. The availability of this classpath file is a kind
of "escape hatch" from Gradle so that we can use handwritten `java` commands to execute our program. Effectively, we are
relying on Gradle as a *build tool* but not as a *run tool* (so to speak). Why would we ever want that? Here are some
examples where we want to execute a program's source code but not in a traditional `./gradlew run`-way:
* [Running a program and its libraries in JShell (the Java REPL)](https://github.com/dgroomes/jshell-playground/tree/main/with-gradle)
* [Running JUnit in standalone mode](https://github.com/dgroomes/junit-playground)


## Instructions

Follow these instructions to build the plugin and use it in the Gradle project.

1. Pre-requisite: Java 21
2. Compile the program's source code:
    * ```shell
      ./gradlew compileJava
      ```
    * Notice the class files in `build/classes/java/main/`
3. Build the classpath reference file:
    * ```shell
      ./gradlew listDependencies
      ```
    * Notice the file `build/runtime-dependencies.txt`. Thanks Gradle!
4. Run the program:
    * ```shell
      ./run.sh
      ```
    * You should see something like this: 
      ```text
      [main] INFO dgroomes.Main - Hello world from the 'main' method!
      ```


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* [x] DONE Make the Gradle `buildSrc` content more idiomatic. It should probably be a plugin.
  * DONE make it a plugin
  * SKIPPED (it's better to show the distinct task executions for illustrative purposes I think) Make the task depend on `build` for convenience?
  * DONE Use the "plugins" block to apply the plugin
  * SKIPPED (there's no config necessary for this plugin so it doesn't make sense) Use the proper file APIs. See [*Working with files in custom tasks and plugins*](https://docs.gradle.org/current/userguide/custom_plugins.html#sec:working_with_files_in_custom_tasks_and_plugins)
  * DONE Identify tasks inputs and outputs whihc enables Gradle's Incremental Build capability.
* [x] DONE Delete the JShell component of the example because it was migrated to my [jshell-playground](https://github.com/dgroomes/jshell-playground/tree/main/with-gradle)
  repository.


## Reference

* [Gradle docs: *Developing Custom Gradle Plugins*](https://docs.gradle.org/current/userguide/custom_plugins.html)
* [Gradle docs: *Authoring Tasks*](https://docs.gradle.org/current/userguide/more_about_tasks.html)
  * [*Task outcomes*](https://docs.gradle.org/current/userguide/more_about_tasks.html#sec:task_outcomes)
  * [*Up-to-date checks (AKA Incremental Builds)*](https://docs.gradle.org/current/userguide/more_about_tasks.html#sec:up_to_date_checks)
