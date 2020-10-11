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

This repository illustrates different concepts, patterns and examples of Gradle via standalone sub-projects. The root of
the project is also a Gradle project just to appease Intellij. Intellij has a hard if it can't pigeonhole a project into
a pre-defined box (e.g. Gradle, Maven). So, I made the root project its own Gradle project. Despite this, each
sub-project can be run completely on their own. They all have their own copies of the Gradle wrapper even! Normally,
this is *not* how you would structure a multi-module Gradle project, but I want each sub-project to be a minimal example
and so I will pay the price of the duplicate Gradle wrappers and `settings.gradle` files.

## Standalone sub-projects

This repository illustrates different concepts, patterns and examples via standalone sub-projects. Each sub-project is
completely independent of the others and do not depend on the root project. This _standalone sub-project constraint_
forces the sub-projects to be complete and maximizes the reader's chances of successfully running, understanding, and
re-using the code.

The sub-projects include:

### `java-preview-features/`

This sub-project shows how to configure Gradle to build and run a project that uses Java [_Preview Features_](https://openjdk.java.net/jeps/12).

See [java-preview-features/README.md](java-preview-features/README.md).

### `java-preview-features-groovy-dsl/`

This sub-project is just like the `java-preview-features/` sub-project but it uses Gradle's original [*Groovy DSL*](https://docs.gradle.org/current/dsl/index.html)
instead of the modern (and preferred, in my opinion) [*Kotlin DSL*](https://docs.gradle.org/current/userguide/kotlin_dsl.html).

See [java-preview-features-groovy-dsl/README.md](java-preview-features-groovy-dsl/README.md).

### `java-early-access/`

This sub-project illustrates how to use early access versions of Java in a Gradle project by leveraging Gradle's forking
and "executable" configuration options.

See [java-early-access/README.md](java-early-access/README.md).

### `consuming-a-maven-bom/`

This sub-project shows how to consume a Maven BOM in a Gradle project and *strictly* override a BOM dependency.

See [consuming-a-maven-bom/README.md](consuming-a-maven-bom/README.md).

## WishList

General clean-ups, TODOs and things I wish to implement for this project:

* DONE Implement a `java-preview-features-kotlin-dsl` sub-project. (Pretty much copy <https://github.com/dgroomes/wiremock-playground/blob/db2684af8617995106e7793ea1348e1d6516bc70/build.gradle.kts>)   
* DONE `java-preview-features` to `java-preview-features-groovy-dsl` and `java-preview-features-kotlin-dsl` to `java-preview-features`
  because the Kotlin DSL is now fully usable and featureful in my experience. This was not quite true a year ago but it
  is really good now! I love the auto-completion.
