package dk.kyuff.pomlint;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

public abstract class SuperMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", readonly = true)
    protected MavenProject project;

    @Parameter(defaultValue = "false", property = "allowEmptyDescription")
    protected boolean allowEmptyDescription;

    @Parameter(defaultValue = "false", property = "allowDependenciesInPomModules")
    protected boolean allowDependenciesInPomModules;

    @Parameter(defaultValue = "false", property = "allowTestScopeDependenciesOutOfOrder")
    protected boolean allowTestScopeDependenciesOutOfOrder;

    @Parameter(defaultValue = "false", property = "allowMixedPropertyNames")
    protected boolean allowMixedPropertyNames;



    /**
     * @param project the project to set
     */
    public void setProject(MavenProject project) {
        this.project = project;
    }

}
