plugins {
    java
    id("dgroomes.dependencies-lister")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.slf4j.api)
    runtimeOnly(libs.slf4j.simple)
}
