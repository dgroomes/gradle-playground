# consuming-a-maven-bom

NOT YET FULLY IMPLEMENTED

This sub-project shows how to consume a Maven BOM in a Gradle project.

---

In particular, this project imports the [Jackson project](https://github.com/FasterXML/jackson) BOM dependency: <https://github.com/FasterXML/jackson-bom>.
So, in theory, Jackson dependencies defined in the BOM can be declared in the Gradle project *without* a version because
the version will be determined by the BOM. Also, in theory, we should be able to specifically override versions of any
dependency defined in the BOM (I'm not sure how to do this!).

While I develop this project, I use the Gradle `dependencies` and `dependencyInsight` tasks to observe the resolved
versions of dependencies. For example:

```
./gradlew dependencies
./gradlew dependencyInsight --dependency jackson-bom
./gradlew dependencyInsight --dependency jackson-databind
```  

### Referenced Materials

* Gradle docs: [Importing Maven BOMs](https://docs.gradle.org/current/userguide/platforms.html#sub:bom_import)
