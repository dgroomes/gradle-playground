plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.kotlin.gradle.plugin)

    // "Vampire's workaround"
    //
    // The GitHub user "Vampire" (Bjorn K.) is a core contributor to Gradle and also came up with a popular workaround
    // for the descire to have access to the main project's version catalog from a 'build-logic' or 'buildSrc' project,
    // and even have Kotlin type-safe accessors over the version catalog entries.
    //
    // GitHub issue #15383 describes the feature request: https://github.com/gradle/gradle/issues/15383
    //
    // And Vampire's workaround is described by his comment on the issue: https://github.com/gradle/gradle/issues/15383#issuecomment-779893192
    //
    // The official Gradle docs also describe the same ergonomics problem, and they offer half of Vampire's workaround.
    // Specifically, the 'versionCatalogs { ... from(files(...)) }' part, but not the `the<LibrariesForLibs` part.
    // See https://github.com/gradle/gradle/blob/3c514f2e6c73f4ac1f21c07ff6cb6d3d2ecba3e8/platforms/documentation/docs/src/docs/userguide/reference/dependency-management/centralizing-dependencies/version_catalogs.adoc?plain=1#L482
    //
    // Overall this ergonomics problem, and the many facets of working around it, is on the high end of complexity and I
    // generally wouldn't recommend for a team's codebase. I would suggest just living with some config/code duplication.
    //
    // I've thought about this a lot (even starting around 2021 when this problem and workaround began). It really nags
    // on me. Now that I understand it better, I'm more at peace with it. One insight I had was that maybe the problem
    // should be reframed as a feature request for a build-logic project to author and publish its own version catalog
    // to the main project, instead of the build-logic naughtily reaching into the main project's TOML versions file.
    //
    // This idea is already explored in this GitHub issue: https://github.com/gradle/gradle/issues/18847 . Unfortunately,
    // there remains a dependency substitution problem which doesn't allow version catalogs to be included as composite
    // builds. In principle, it should be possible. Keep watching that GH issue.
    //
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}
