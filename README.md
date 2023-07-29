# gradle-playground

📚 Learning and exploring the Gradle Build Tool.

> From mobile apps to microservices, from small startups to big enterprises, Gradle helps teams build, automate and
> deliver better software, faster.
> -- <cite>https://gradle.org</cite>


## Why is this useful?

Gradle can be complex to configure correctly because it is a powerful DSL with a rich set of features and plugins. It's
hard to remember the exact incantations to write in a `build.gradle` (or `build.gradle.kts` for that matter) to
configure, for example, the enablement of Java language preview features or JUnit test logging.

While all the information you need to configure a Gradle project is technically available in the highly thorough and
accurate [official Gradle documentation](https://docs.gradle.org/current/userguide/userguide.html) and embedded in
[executable sample projects within the Gradle source code repository](https://github.com/gradle/gradle/tree/master/subprojects/docs/src/samples),
this repository contains my own personal examples that I need to refer back to continuously in my day-to-day development.
In other words, this repository whittles down the full scale of Gradle to a few files for my own quick reference.


## Standalone subprojects

This repository illustrates different concepts, patterns and examples via standalone subprojects. Each subproject is
completely independent of the others and do not depend on the root project. This _standalone subproject constraint_
forces the subprojects to be complete and maximizes the reader's chances of successfully running, understanding, and
re-using the code.

The subprojects include:

### `java-preview-features/`

This subproject shows how to configure Gradle to build and run a project that uses Java [_Preview Features_](https://openjdk.java.net/jeps/12).

See the README in [java-preview-features/](java-preview-features/).

### `java-toolchain/`

This subproject illustrates how to leverage Gradle's [Toolchains for JVM projects](https://docs.gradle.org/current/userguide/toolchains.html)
to use an early-access version of Java in a Gradle project.

See the README in [java-toolchain/](java-toolchain/).

### `consuming-a-maven-bom/`

This subproject shows how to consume a Maven BOM in a Gradle project and *strictly* override a BOM dependency.

See the README in [consuming-a-maven-bom/](consuming-a-maven-bom/).

### `multi-module/`

This subproject illustrates a multi-module Gradle project.

See the README in [multi-module/](multi-module/).

### `plugin/`

This subproject shows how to write and apply a custom Gradle plugin. It also showcases [Gradle's `buildSrc` feature](https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#sec:build_sources).

See the README in [plugin/](plugin/).

### `java-modules/`

An example Gradle project that uses the Java Platform Module System (JPMS).

See the README in [java-modules/](java-modules/).


## Wish List

General clean-ups, changes and things I wish to implement for this project:

* [x] DONE Revive `java-toolchain` and upgrade it to Java 20 (because Java 18 I can't download easily so I'm going to just
  unload this subproject from the `settings.gradle.kts` file for now)
* [x] DONE Updates for 2023.
* [x] DONE Implement a `java-preview-features-kotlin-dsl` subproject. (Pretty much copy <https://github.com/dgroomes/wiremock-playground/blob/db2684af8617995106e7793ea1348e1d6516bc70/build.gradle.kts>)   
* [x] DONE `java-preview-features` to `java-preview-features-groovy-dsl` and `java-preview-features-kotlin-dsl` to `java-preview-features`
   because the Kotlin DSL is now fully usable and featureful in my experience. This was not quite true a year ago but it
   is really good now! I love the auto-completion.
* [x] DONE Add a multi-module Gradle project example. Similar to the one defined at <https://github.com/dgroomes/kafka-playground/blob/557243bea2960a18e5b11da04f2cec46989576ee/build.gradle.kts#L10> 
* [x] DONE Add a subproject that showcases `buildSrc/`
* [x] DONE Remove `mavenLocal()`. Great official explanation in the [Gradle docs](https://docs.gradle.org/current/userguide/declaring_repositories.html#sec:case-for-maven-local)
* [x] DONE Upgrade to Gradle 6.8.1 (when it comes out)
* [x] DONE Build in GitHub Actions
* [ ] Can we get both Java 15 and Java 16 in the GitHub Actions build environment? If we could, then we can include the 'java-toolchain/'
   subproject to the 'assembleAll' Gradle task.
* [ ] Incorporate Gradle's [test fixtures](https://docs.gradle.org/current/userguide/java_testing.html#sec:java_test_fixtures)
   feature in one of the subprojects
