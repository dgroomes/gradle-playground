plugins {
    application
}

java {
    modularity.inferModulePath.set(true)
}

dependencies {
    implementation(project(":echo"))
}

application {
    mainModule.set("dgroomes.runner")
    mainClass.set("dgroomes.runner.Main")
}
