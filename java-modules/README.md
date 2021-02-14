# java-modules

An example Gradle project that uses the Java Platform Module System (JPMS). 

## Description

There are a few things to highlight about this project vs. a non-JPMS project:

* The `module-info.java` files
* Enabling JPMS support in Gradle with `modularity.inferModulePath.set(true)` in the `java { ... }` configuration block
* Configuring the Gradle *Application Plugin* to work with modules with `mainModule.set("dgroomes.runner")`
* When you build a distribution with `./gradlew install`, the shell/start script that is generated (`build/install/runner/bin/runner`)
  by the Gradle *Application Plugin* defines a `MODULE_PATH` variable in addition to the `CLASSPATH` variable that normally
  exists.

Tip: disassemble a `module-info.class` file to view its content using `javap`. For example: `javap --module-path . module-info.class`.
I've found this to be helpful while learning about JPMS.

---
**WARNING**: Gradle's support for JPMS is still incubating as of Gradle 6.8.2 

---

## Instructions

* Use Java 11
* Build and run the program:
  * `./gradlew run`

## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* DONE Incorporate Jackson as a dependency to this project because apparently Jackson has [some integrations with JPMS](https://github.com/FasterXML/jackson-databind/blob/a2c8c652a0fc01f95f819b65a159a9449af6c0d2/src/moditect/module-info.java#L2).
  It would be useful to show how to incorporate a Gradle dependency that publishes module info. How does that work?

## Referenced material

* [Gradle docs: *Build Java Modules Sample*](https://docs.gradle.org/current/samples/sample_java_modules_multi_project.html)
* [Gradle docs: *The Application Plugin: Building applications using the Java Module System*](https://docs.gradle.org/current/userguide/application_plugin.html#sec:application_modular)
* [Oracle: *Understanding Java 9 Modules*](https://www.oracle.com/corporate/features/understanding-java-9-modules.html)
