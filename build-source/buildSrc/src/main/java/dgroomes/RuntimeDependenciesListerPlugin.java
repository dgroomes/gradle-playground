package dgroomes;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * A custom Gradle plugin to register a task to list the project's runtime dependencies in a file.
 */
public class RuntimeDependenciesListerPlugin implements Plugin<Project> {

    /**
     * Print the runtime classpath (given by the Gradle source set named "main") to "build/runtime-classpath.txt"
     */
    @Override
    public void apply(Project project) {
        project.getTasks().register("printClassPath", dgroomes.PrintClassPath.class);
    }
}
