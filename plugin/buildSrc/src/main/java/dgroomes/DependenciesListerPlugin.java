package dgroomes;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.SourceSetContainer;

/**
 * A custom Gradle plugin to register a task to list the project's runtime dependencies in a file.
 */
public class DependenciesListerPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getTasks().register("listDependencies", ListDependenciesTask.class, task -> {
            // I update this plugin to be compatible with the Gradle config cache, and in so doing had to push work into
            // this 'configurationAction' closure. That goes against my original design goal of having the task
            // encapsulate its own config/code. I'm curious if there is a more idiomatic way to do this... I'm sure
            // there is, but I can't figure it out right now.
            var sourceSets = project.getExtensions().getByType(SourceSetContainer.class);
            var mainSourceSet = sourceSets.getByName("main");

            task.getRuntimeClasspath().setFrom(mainSourceSet.getRuntimeClasspath());
            task.getDependenciesListFile().set(project.getLayout().getBuildDirectory().file(ListDependenciesTask.DEPENDENCIES_OUTPUT_FILE_NAME));
        });
    }
}
