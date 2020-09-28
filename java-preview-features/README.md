# java-preview-features

This project is a working example of a Gradle project configured to support the latest version of Java (or at least, the
latest version of Java at the time of writing!). This project uses *Gradle's Groovy DSL*.

---

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
