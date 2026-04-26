# multi-project-cross-configuration

A multi-project Gradle build configured with cross-project configuration using the `allprojects` and `subprojects` DSL constructs.


## Overview

Structuring code across multiple well-defined modules is a natural step in growing a software project. The Gradle docs refers to this concept as a ["multi-project build" in their excellent user guide for the subject][multi-project-builds]. I prefer to use the term "multi-module project" because it applies generally to any type of programming ecosystem and project.

Here, we use Gradle's `allprojects` and `subprojects` DSL constructs to configure a `project-a` and a `project-b` subproject. The [Gradle docs actually warn against using them][avoiding-allprojects-and-cross-projects]:

> An improper way to share build logic between subprojects is cross-project configuration via the `subprojects {}` and `allprojects {}` DSL constructs.
> 
> With cross-project configuration, build logic can be injected into a subproject which is not obvious when looking at its build script.
> 
> In the long run, cross-project configuration usually grows in complexity and becomes a burden. Cross-project configuration can also introduce configuration-time coupling between projects, which can prevent optimizations like configuration-on-demand from working properly.

Despite this warning, the `allprojects` and `subprojects` constructs are used so frequently in practice that I need to understand and have a reference for them.


## Instructions

The interesting part of this project is the Gradle configuration. So, read the `build.gradle.kts` files. But still, it's
useful to execute the example applications to prove that it works. Follow the below steps to build and run the programs:

1. Pre-requisite: Java 25
2. Run `project-a`:
   - ```shell
     ./gradlew :project-a:run
     ```
   - It should print something like the following.
   - ```text
     > Task :project-a:run
     [main] INFO dgroomes.multi_project_cross_configuration.project_a.MainA - Hello world from 'project-a'!
     ```
3. Run `project-b`:
   - ```shell
     ./gradlew :project-b:run
     ```
   - It should print something like the following.
   - ```text
     > Task :project-b:run
     [main] INFO dgroomes.multi_project_cross_configuration.project_b.MainB - Hello world from 'project-b'!
     ```
4. Use the Kotlin DSL accessor report as a debugging tool
   - Gradle defines a `kotlinDslAccessorsReport` task which is a neat way to make sense of the Kotlin DSL accessors Gradle generated for a specific project. Run it for the root project with the following command.
   - ```shell
     ./gradlew :kotlinDslAccessorsReport
     ```
   - Notice what is *not* there: the root project report does not include an `implementation(...)` accessor. Next, run it for `project-b`.
   - ```shell
     ./gradlew :project-b:kotlinDslAccessorsReport
     ```
   - In this report, you'll find the following.
   - ```kotlin
     fun DependencyHandler.`implementation`(dependencyNotation: Any): Dependency? = add("implementation", dependencyNotation)
     ```
   - This shows that Gradle generated those accessors for the subproject. This is because `project-b` had the `java` plugin applied to it and the `java` plugin generated that accessor.


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


## Reference

- [Gradle user guide: *Multi-Project Builds*][multi-project-builds]
- [Gradle user guide: *Sharing Build Logic*][sharing-build-logic]

[avoiding-allprojects-and-cross-projects]: https://docs.gradle.org/current/userguide/sharing_build_logic_between_subprojects.html#sec:convention_plugins_vs_cross_configuration
[multi-project-builds]: https://docs.gradle.org/current/userguide/multi_project_builds.html
[sharing-build-logic]: https://docs.gradle.org/current/userguide/sharing_build_logic_between_subprojects.html
