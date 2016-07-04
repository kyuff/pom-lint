package dk.kyuff.pomlint;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

public class SuperMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", readonly = true)
    protected MavenProject project;

    @Parameter(defaultValue = "false", property = "allowEmptyDescription")
    protected boolean allowEmptyDescription;

    public void execute() throws MojoExecutionException, MojoFailureException {

    }

    /**
     * @param project the project to set
     */
    public void setProject(MavenProject project) {
        this.project = project;
    }

    /**
     * Override the rule to disallow empty description tags
     *
     * @param allowEmptyDescription true if empty descriptions are allowed
     */
    public void setAllowEmptyDescription(boolean allowEmptyDescription) {
        this.allowEmptyDescription = allowEmptyDescription;
    }
}
