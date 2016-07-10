package dk.kyuff.pomlint;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

public abstract class SuperMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", readonly = true)
    protected MavenProject project;

    /**
     * Writing a good description helps two times:
     * 1) If the module description is too hard to write, it's probably
     * because the modules design is lacking somehow.
     * Perhaps it has too much responsibility?
     * 2) When maintaining the module it is often helpful for your future self
     * or another developer to have a proper description that tells what
     * this module is all about.
     */
    @Parameter(defaultValue = "false", property = "allowEmptyDescription")
    boolean allowEmptyDescription;

    /**
     * By having dependencies in a parent module it often becomes
     * very hard to maintain the sub-modules dependencies.
     * It's often not obvious were a dependency is declared,
     * and you lose the ability to move modules in your file
     * structure as they have implicit requirements from parent
     * modules.
     */
    @Parameter(defaultValue = "false", property = "allowInherentDependency")
    boolean allowInherentDependency;

    /**
     * Having test scope at the end helps maintain a better overview of all the
     * dependencies in the module.
     */
    @Parameter(defaultValue = "false", property = "allowTestScopeOutOfOrder")
    boolean allowTestScopeOutOfOrder;

    /**
     * By mixing dotted and dashed naming conventions pom files become
     * harder to read and maintain. This rule enforces the use of only
     * one naming convention
     */
    @Parameter(defaultValue = "false", property = "allowMixedPropertyNames")
    boolean allowMixedPropertyNames;

    /**
     * It's a good idea to have the module name contain or be the artifactId.
     * By enforcing this rule, it becomes easier to find the culprit when a single module fails
     * in a large multi-module build. Often the alternative would be to have to do a text search in
     * the pom.xml files to find the failing module.
     */
    @Parameter(defaultValue = "false", property = "allowModuleNamesToDiffer")
    boolean allowModuleNamesToDiffer;


    /**
     * @param project the project to set
     */
    public void setProject(MavenProject project) {
        this.project = project;
    }

}
