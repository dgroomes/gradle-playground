package dgroomes;

import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import org.gradle.tooling.model.build.BuildEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        var externalProjectDir = new File("/Users/dave/repos/personal/arrow-playground/basic");
        log.info("Let's inspect the Gradle 'build environment' for the project in the directory: {}", externalProjectDir);
        var gradleConnector = GradleConnector.newConnector().forProjectDirectory(externalProjectDir);

        try (ProjectConnection connection = gradleConnector.connect()) {
            var buildEnvironment = connection.getModel(BuildEnvironment.class);
            log.info("The Gradle Tooling API found this 'build environment' information:");
            log.info("Gradle version: {}", buildEnvironment.getGradle().getGradleVersion());
            log.info("Gradle home: {}", buildEnvironment.getGradle().getGradleUserHome());
        }
    }
}
