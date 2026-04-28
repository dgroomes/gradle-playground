plugins {
    // We apply the pre-compiled script plugin. And in so doing, there's no need to apply the Kotlin plugin.
    id("my.kotlin-conventions")
    application
}

dependencies {
    // Because we applied the conventions plugin, we can omit the JUnit dependency boilerplate. Nice!
    implementation(libs.jackson.databind)
}

application {
    mainClass.set("dgroomes.pre_compiled_script_plugin.AppKt")
}
