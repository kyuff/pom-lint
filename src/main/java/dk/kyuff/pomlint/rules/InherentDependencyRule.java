package dk.kyuff.pomlint.rules;

import dk.kyuff.pomlint.DisableRule;
import dk.kyuff.pomlint.Rule;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

public class InherentDependencyRule extends DisableRule {

    private int size = 0;

    public boolean valid(MavenProject project) {
        if ("pom".equals(project.getPackaging()) && project.getModules().size() > 0) {

            size = project.getDependencies().size();
            return size == 0;
        }
        return true;
    }

    public String getName() {
        return "Inherent Dependency";
    }

    public void stateError(Log log) {
        if (size == 1) {
            log.error("This is a pom module with child modules. And have a dependency!");
        } else {
            log.error(String.format("This is a pom module with child modules. And it have %d dependencies!", size));
        }
        log.error("By having dependencies in a parent module it often becomes");
        log.error("very hard to maintain the sub-modules dependencies.");
        log.error("It's often not obvious were a dependency is declared,");
        log.error("and you lose the ability to move modules in your file");
        log.error("structure as they have implicit requirements from parent");
        log.error("modules.");
    }
}
