# java-modules

An example Gradle project that uses the Java Platform Module System (JPMS). 

## Description

There are a few things to highlight about this project vs. a non-JPMS project:

* The `module-info.java` files
* Configuring the Gradle *Application Plugin* to work with modules with `mainModule.set("dgroomes.runner")`
* When you build a distribution with `./gradlew install`, the shell/start script that is generated (`build/install/runner/bin/runner`)
  by the Gradle *Application Plugin* defines a `MODULE_PATH` variable in addition to the `CLASSPATH` variable that normally
  exists.

Tip: disassemble a `module-info.class` file to view its content using `javap`. For example: `javap --module-path . module-info.class`.
I've found this to be helpful while learning about JPMS.

## Instructions

1. Use Java 17
2. Build and run the program:
   * `./gradlew run`

## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* DONE Incorporate Jackson as a dependency to this project because apparently Jackson has [some integrations with JPMS](https://github.com/FasterXML/jackson-databind/blob/a2c8c652a0fc01f95f819b65a159a9449af6c0d2/src/moditect/module-info.java#L2).
  It would be useful to show how to incorporate a Gradle dependency that publishes module info. How does that work?
* DONE (added slf4j 1.7.x. The 1.8.x and 2.x branches are modularized but are beta) Incorporate a non-modularized
  dependency to this project. This is an almost universal use-case because so many libraries are not modularized. This
  requires declaring an "automatic module".
* Patch the slf4j dependency to be modular (will this let me `jlink` it without needing `jdeps`?). For reference about how to do this, see:
    * [StackOverflow Q&A: *How to inject module declaration into JAR?*](https://stackoverflow.com/q/47222226) (note: this
      makes sense for one jar but I couldn't get it to work with multiple jars that have dependency relationships between them, like slf4j)
    * [Gradle docs: *Building Java Modules with Legacy Libraries Sample*](https://docs.gradle.org/current/samples/sample_java_modules_with_transform.html) (note: this is the ticket!)
* DONE Gradle's module support was promoted in Gradle 7.x. Read the [release notes](https://docs.gradle.org/7.0.2/release-notes.html).
  In particular, the `inferModulePath` call is no longer required. Delete it.
* DONE Showcase JPMS's strength when it comes to strongly encapsulating implementation details. For example, can I have
  classes that are used as implementation details in a package (or maybe sub-package?) and then export just a particular
  "ordained" package?

## Reference

* [Gradle docs: *Build Java Modules Sample*](https://docs.gradle.org/current/samples/sample_java_modules_multi_project.html)
* [Gradle docs: *The Application Plugin: Building applications using the Java Module System*](https://docs.gradle.org/current/userguide/application_plugin.html#sec:application_modular)
* [Oracle: *Understanding Java 9 Modules*](https://www.oracle.com/corporate/features/understanding-java-9-modules.html)
