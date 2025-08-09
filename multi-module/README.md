# multi-module

A **poorly configured** multi-module Gradle project.


## Overview

This project is me learning how to configure multi-module Gradle projects. Read more about Gradle's support for
multi-project builds at the [official doc site](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:multi_project_builds).

This project is a poor configuration of a multi-module Gradle project because it uses "cross-configuring" from the root
`build.gradle.kts` file. This is not recommend. See the extensive note in that file. Instead, you should use a
combination of pre-compiled script plugins and keeping subproject-specific configuration in the subproject instead of
trying to "control it from afar" in the root `build.gradle.kts`.


## Instructions

The interesting part of this project is the Gradle configuration. So, read the `build.gradle.kts` files. But still, it's
useful to execute the example applications to prove that it works. Follow the below steps to build and run the programs:

1. Pre-requisite: Java 21
2. Run `module-a`:
   - ```shell
     ./gradlew :module-a:run
     ```
   - It should print something like the following.
   - ```text
     > Task :module-a:run
     [main] INFO dgroomes.multi_module.module_a.MainA - Hello world from 'module-a'!
     ```
3. Run `module-b`:
   - ```shell
     ./gradlew :module-b:run
     ```
   - It should print something like the following.
   - ```text
     > Task :module-b:run
     [main] INFO dgroomes.multi_module.module_b.MainB - Hello world from 'module-b'!
     ```


## Wish List

General clean-ups, changes and things I wish to implement for this project:

- [x] DONE (update: yes use the "java-platform" plugin) Is it possible to define a Maven BOM in the root project and consume it from the subprojects? The effect of this is
  that the subprojects should not have to declare the dependency versions and the versions defined by the BOM should be
  used. I have the "consuming-a-maven-bom" subproject, but in this case we want to *define* our own BOM. Consider creating a
  whole new subproject but I figure that the "common dependency version declaration" use case is almost universal for
  multi-module projects, and I want "common dependency version declaration" implemented in "multi-module/". I think a BOM
  is how to do it because there is some problem (maybe will be fixed in Gradle 7?) about root project dependency declarations
  being applied to subprojects even if you don't want them (not good).
- [x] OBSOLETE (I'm using the "version catalog" instead of this approach) Recently I'm preferring Gradle ["pre-compiled script plugins"](https://github.com/dgroomes/http-client-server-playground/blob/main/buildSrc/src/main/kotlin/common.gradle.kts)
  –also called convention scripts– instead of the platform feature via the `java-platform` plugin. Maybe the platform
  feature is best suited when it is published, like the Jackson or JUnit BOMs. But for a given project, I think the
  bespoke versioning constraints are best done in a conventions script. Replace the platform with a conventions script. 
- [x] DONE Use [Gradle's version catalogs](https://docs.gradle.org/current/userguide/platforms.html#sub:version-catalog)
