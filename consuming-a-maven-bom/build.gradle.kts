plugins {
    java
}

repositories {
    mavenLocal()
    jcenter()
}

val jacksonBomVersion = "2.11.2" // releases: https://github.com/FasterXML/jackson/wiki/Jackson-Releases
val jacksonDatabindVersion = "2.11.1" // let's try to force a specific version of jackson-databind different from what is defined in the BOM

dependencies {
    // Can we enforce the specific version (2.11.1) of jackson-databind that we want? Can we use Gradle constraints'
    // https://docs.gradle.org/current/userguide/dependency_constraints.html#sec:adding-constraints-transitive-deps
    constraints {
        implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonDatabindVersion") // this attempt to override doesn't work? the bom version seems to be an unchangeable "constraint"
    }

    implementation(platform("com.fasterxml.jackson:jackson-bom:$jacksonBomVersion"))
    implementation("com.fasterxml.jackson.core:jackson-annotations")
    implementation("com.fasterxml.jackson.core:jackson-core")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonDatabindVersion") { // this attempt to override doesn't work! the bom version seems to be an unchangeable "constraint"
        version {
            strictly(jacksonDatabindVersion) // this attempt to override the *DOES* work! See https://docs.gradle.org/current/userguide/rich_versions.html#sec:strict-version
        }
    }
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-csv")

}
