# multi-module

This sub-project illustrates a multi-module Gradle project.

---

Read more about Gradle's support for Multi-project builds at the [official doc site](https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:multi_project_builds).

## Instructions

* The interesting part of this project is the Gradle configuration. So, read the `build.gradle.kts` files. But still, it's
  useful to execute the example applications to prove that it works. Follow the below steps to build and run the applications.
* Run `module-a`:
  * `./gradlew :module-a:run`
* Run `module-b`:
  * `./gradlew :module-b:run`

## Wish List

General clean-ups, changes and things I wish to implement for this project:

* DONE (update: yes use the "java-platform" plugin) Is it possible to define a Maven BOM in the root project and consume it from the sub-projects? The effect of this is
  that the sub-projects should not have to declare the dependency versions and the versions defined by the BOM should be
  used. I have the "consuming-a-maven-bom" sub-project, but in this case we want to *define* our own BOM. Consider creating a
  whole new sub-project but I figure that the "common dependency version declaration" use case is almost universal for
  multi-module projects, and I want "common dependency version declaration" implemented in "multi-module/". I think a BOM
  is how to do it because there is some problem (maybe will be fixed in Gradle 7?) about root project dependency declarations
  being applied to sub-projects even if you don't want them (not good).
