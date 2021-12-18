plugins {
    application
}

val slf4jVersion = "1.7.32" // SLF4J releases: http://www.slf4j.org/news.html

java {
    modularity.inferModulePath.set(true)
}

dependencies {
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")
    implementation(project(":echo"))
}

application {
    mainModule.set("dgroomes.runner")
    mainClass.set("dgroomes.runner.Main")
}
