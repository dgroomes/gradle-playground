# gradle-playground

Learning and exploring Gradle (the #1/#2 most popular Java build tool).

> From mobile apps to microservices, from small startups to big enterprises, Gradle helps teams build, automate and
> deliver better software, faster.
> -- <cite>https://gradle.org</cite>


### Description

This repo is a working example of a Gradle project configured to support the latest version of Java (or at least, the
latest version of Java at the time of writing!). Why is this useful? Gradle can be complex to configure correctly
because it is a powerful DSL with a rich set of features and plugins. It's hard to remember the exact incantations to
write in a `build.gradle` (or `build.gradle.kts` for that matter) to configure, for example, Java language preview
features or JUnit test logging.

This repo in particular showcases the new Java 15 _Records_ language feature which is a preview feature hidden behind
the `--enable-preview` Java flag.

You will need to use [Intellij IDEA 2020.2 or later to get Java 15 support](https://blog.jetbrains.com/idea/2020/09/java-15-and-intellij-idea/).

### Instructions

* Use Java 15
* Build and run the application with `./gradlew run`. It should output the following:
    ```
    > Task :compileJava
    > Task :processResources NO-SOURCE
    > Task :classes
    > Task :run
    MyMessage[message=Hello world.]
    
    BUILD SUCCESSFUL in 884ms
    2 actionable tasks: 2 executed
    ```
  * Run the tests with `./gradlew test`
