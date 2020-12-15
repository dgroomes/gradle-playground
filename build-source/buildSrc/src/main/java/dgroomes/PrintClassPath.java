package dgroomes;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.SourceSetContainer;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.StringJoiner;

/**
 * We need to print the runtime classpath to a file so that the `run.sh` script can konw the classpath and pass it to jshell.
 */
public class PrintClassPath extends DefaultTask {

    /**
     * Print the runtime classpath (given by the Gradle source set named "main") to "build/runtime-classpath.txt"
     */
    @TaskAction
    public void printClassPath() throws IOException {
        var sourceSets = ((SourceSetContainer) getProject().getExtensions().getByName("sourceSets"));
        var mainSourceSet = sourceSets.getByName("main");

        // Build the classpath
        var joiner = new StringJoiner(":");
        for (File file : mainSourceSet.getRuntimeClasspath()) {
            if (file.isFile()) {
                joiner.add(file.toString());
            }
        }
        var classpath = joiner.toString();

        // Create the build directory
        var buildDir = getProject().getBuildDir();
        buildDir.mkdir();

        // Delete the classpath file if it already exists
        var file = new File(getProject().getBuildDir(), "runtime-classpath.txt");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();

        // Write the contents to the file
        Files.writeString(file.toPath(), classpath);
        getLogger().quiet("Wrote runtime classpath to {}", file);
    }
}
