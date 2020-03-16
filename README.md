# example-gradle-java-14

Example Gradle project showcasing Java 14 support and the new _Records_ language feature.

### Description

Is it Q1/Q2 2020 and you want to upgrade your Gradle project to Java 14 and use the new _Records_ language feature? If 
yes, here is an example Gradle project with Java 14 with preview features enabled.

With the release of Gradle 6.3-rc-1 it is now easy to use Java 14 with Gradle because this version of Gradle added 
support for Java 14. See the release notes: <https://docs.gradle.org/6.3-rc-1/release-notes.html#support-for-java-14>.

You might also want to use Intellij IDEA 2020.1 EAP or later because 2020.1 EAP is the first release of Intellij to also
support Java 14. See the release notes: <https://blog.jetbrains.com/idea/2020/01/intellij-idea-2020-1-eap/>.

### Instructions

* Use Java 14
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