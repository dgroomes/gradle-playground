package dgroomes;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.SourceSetContainer;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.StringJoiner;

/**
 * List the project's runtime dependencies and print them into a file.
 */
public class ListDependenciesTask extends DefaultTask {

    public static final String DEPENDENCIES_OUTPUT_FILE_NAME = "runtime-dependencies.txt";

    /**
     * List the project's runtime dependencies. This is given by the Gradle source set named "main". It is printed to
     * "build/runtime-dependencies.txt"
     */
    @TaskAction
    public void listDependencies() throws IOException {
        var sourceSets = ((SourceSetContainer) getProject().getExtensions().getByName("sourceSets"));
        var mainSourceSet = sourceSets.getByName("main");

        // Join the dependencies by the colon character. This format is friendly as an argument to describe a classpath.
        var joiner = new StringJoiner(":");
        for (File file : mainSourceSet.getRuntimeClasspath()) {
            if (file.isFile()) {
                joiner.add(file.toString());
            }
        }
        var joined = joiner.toString();

        // Create the build directory
        var buildDir = getProject().getBuildDir();
        buildDir.mkdir();

        // Delete the dependencies file if it already exists
        var file = new File(getProject().getBuildDir(), DEPENDENCIES_OUTPUT_FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();

        // Write the contents to the file
        Files.writeString(file.toPath(), joined);
        getLogger().quiet("Wrote runtime dependencies to '{}'", file);
    }
}
