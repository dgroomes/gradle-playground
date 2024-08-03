# consuming-a-maven-bom

Consume a Maven BOM in a Gradle project and *strictly* override a BOM dependency.


## Overview

In particular, this project imports the [Jackson project](https://github.com/FasterXML/jackson) BOM dependency: <https://github.com/FasterXML/jackson-bom>.
This allows Jackson dependencies defined in the BOM to be declared in the Gradle project *without* a version because
the version will be determined by the BOM (pretty cool!). Also, we are able to specifically override versions of any
dependency defined in the BOM. See the block comments in the `build.gradle.kts` file for details.

We can make sense of the final dependency tree if we look at the `gradle.lockfile` which is generated when we use
Gradle's [dependency locking](https://docs.gradle.org/current/userguide/dependency_locking.html) feature.


## Instructions

Follow these instructions to resolve and inspect the dependencies.

1. Pre-requisite: Java 21
2. Read the `build.gradle.kts` file
   * This file has code comments that describe the core concepts. Read the file in detail.
3. Trigger dependency resolution and lock the dependencies
   * ```shell
     ./gradlew dependencies --write-locks
     ```
   * Note: Using the `dependencies` task is arbitrary. Any task that resolves dependencies will work.
   * Running a dependency-resolving task with `--write-locks` only has an effect on this project when there is a newer
     version of the Jackson BOM available than there was when this task was last run. The project declares a dynamic
     version dependency on the Jackson BOM. For example, if the project declares `2.17.+` then if `2.17.2` is the latest
     version of the Jackson BOM but the project was last built when `2.17.1` was the latest version, then this task will
     update the `gradle.lockfile` to use `2.17.2`.
4. Make some dependency observations
   * Study the `gradle.lock` file. This is a convenient way to get a holistic view of the resolved dependencies in the
     project. Alternatively, use any of the following commands to inspect the resolved dependencies in the project.
     These commands are especially helpful in describing when dependency rules override one another.
   * ```shell
     ./gradlew dependencies
     ```
   * ```shell
     ./gradlew dependencies --configuration compileClasspath
     ```
   * ```shell
     ./gradlew dependencyInsight --dependency jackson-bom
     ```
   * ```shell
     ./gradlew dependencyInsight --dependency jackson-databind
     ```


## Wish List

General clean-ups, changes and things I wish to implement for this project:

* [x] DONE Consider using dependency locking as way to more concretely show what versions are being used. See the
  [Gradle docs on dependency locking](https://docs.gradle.org/current/userguide/dependency_locking.html).


## Reference

* Gradle docs: [_Importing Maven BOMs_](https://docs.gradle.org/current/userguide/platforms.html#sub:bom_import)
* Gradle docs: [_Declaring Rich Versions_](https://docs.gradle.org/current/userguide/rich_versions.html#sec:strict-version)
* Blog post (brilliant post as always, by mrhaki): [_Use bill of materials (BOM) As Dependency Constraints_](https://mrhaki.blogspot.com/2019/04/gradle-goodness-use-bill-of-materials.html)
* GitHub issue: [_Support overriding BOM version properties with ext_](https://github.com/gradle/gradle/issues/9160) ?
