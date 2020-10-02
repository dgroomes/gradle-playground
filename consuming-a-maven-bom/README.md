# consuming-a-maven-bom

NOT YET FULLY IMPLEMENTED

This sub-project shows how to consume a Maven BOM in a Gradle project.

---

In particular, this project imports the [Jackson project](https://github.com/FasterXML/jackson) BOM dependency: <https://github.com/FasterXML/jackson-bom>.
This allows Jackson dependencies defined in the BOM to be declared in the Gradle project *without* a version because
the version will be determined by the BOM (pretty cool!). Also, in theory, we should be able to specifically override
versions of any dependency defined in the BOM. Unfortunately, this does [not seem to be possible for the Jackson BOM](https://github.com/gradle/gradle/issues/9160)).
I 

While I develop this project, I use the Gradle `dependencies` and `dependencyInsight` tasks to observe the resolved
versions of dependencies. For example:

```
./gradlew dependencies
./gradlew dependencyInsight --dependency jackson-bom
./gradlew dependencyInsight --dependency jackson-databind
```  

### Referenced Materials

* Gradle docs: [Importing Maven BOMs](https://docs.gradle.org/current/userguide/platforms.html#sub:bom_import)
* Gradle docs: [_Declaring Rich Versions_](https://docs.gradle.org/current/userguide/rich_versions.html#sec:strict-version)
* Blog post (brilliant post as always, by mrhaki): [_Use bill of materials (BOM) As Dependency Constraints_](https://mrhaki.blogspot.com/2019/04/gradle-goodness-use-bill-of-materials.html)
* GitHub issue: that says [BOM versions cannot be changed](https://github.com/gradle/gradle/issues/9160)  under any circumstance?
