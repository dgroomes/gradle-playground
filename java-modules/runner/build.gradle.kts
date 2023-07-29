plugins {
    id("common")
    application
}

dependencies {
    implementation(libs.slf4j.api)
    runtimeOnly(libs.slf4j.simple)
    implementation(project(":echo"))
}

application {
    mainModule.set("dgroomes.runner")
    mainClass.set("dgroomes.runner.Main")
}
