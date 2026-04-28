# pre-compiled-script-plugin

Using a *pre-compiled script plugin* to apply common conventions to Gradle subprojects.


## Overview

While building up a multi-project Gradle build, you will certainly settle on a common stanza of config that you need to repeat over and over across the subprojects. You will be tempted to extract this common configuration/code into one location. A popular option is using the `subprojects` and `allprojects` Gradle DSL constructs to house the common config. Alternatively, you could take a lower-level approach and create a custom binary plugin. An option in-between those two is creating a [*pre-compiled script plugin*][pre-compiled-script-plugins].

This project shows how to move common (or *conventional*) Kotlin and JUnit configuration into a pre-compiled script plugin in a `build-logic` build. `build-logic` is just an [included build][composite-builds] that produces build logic, such as pre-compiled script plugins, for the main build.

Importantly, this project also showcases *Vampire's workaround*. See the detailed note in `build-logic/build.gradle.kts`


## Instructions

Follow these instructions to build, test, and run the demo program:

1. Pre-requisite: Java 25
2. Run the tests:
   - ```shell
     ./gradlew :app:test
     ```
3. Run the application:
   - ```shell
     ./gradlew :app:run
     ```
   - It should print something like the following.
   - ```text
     > Task :app:run
     Greeting properties: message; JSON: {"message":"Hello from Kotlin conventions!"}
     ```


## Reference

- [Gradle docs: *Best Practices for Structuring Builds*][best-practices-structuring-builds]
- [Gradle docs: *Composite Builds*][composite-builds]
- [Gradle docs: *Pre-compiled script plugins*][pre-compiled-script-plugins]
- [Gradle docs: *Version Catalogs*][version-catalogs]

[best-practices-structuring-builds]: https://docs.gradle.org/current/userguide/best_practices_structuring_builds.html
[composite-builds]: https://docs.gradle.org/current/userguide/composite_builds.html
[pre-compiled-script-plugins]: https://docs.gradle.org/current/userguide/implementing_gradle_plugins_precompiled.html
[version-catalogs]: https://docs.gradle.org/current/userguide/version_catalogs.html
