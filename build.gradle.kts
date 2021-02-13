/**
 * A convenience task to assemble all of the included projects. This is useful as a quick smoke test of the Gradle config
 * and a general environmental test.
 */
tasks.register("assembleAll") {
    dependsOn(gradle.includedBuild("consuming-a-maven-bom").task(":assemble"))
    dependsOn(gradle.includedBuild("java-preview-features").task(":assemble"))
    dependsOn(gradle.includedBuild("java-preview-features-groovy-dsl").task(":assemble"))

    // Skip 'java-toolchain' because it requires Java 16 and I don't know how to get multiple Java's installed in the
    // GitHub Actions build environment.
//    dependsOn(gradle.includedBuild("java-toolchain").task(":assemble"))

    dependsOn(gradle.includedBuild("multi-module").task(":module-a:assemble"))
    dependsOn(gradle.includedBuild("multi-module").task(":module-b:assemble"))
    dependsOn(gradle.includedBuild("plugin").task(":assemble"))
    dependsOn(gradle.includedBuild("java-modules").task(":runner:assemble"))
}
