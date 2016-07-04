package dk.kyuff.pomlint.rules;

import dk.kyuff.pomlint.Rule;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

public class PomDependencyRule implements Rule {

    private int size = 0;

    public boolean valid(MavenProject project) {
        if ("pom".equals(project.getPackaging())) {
            size = project.getDependencies().size();
            return size == 0;
        }
        return true;
    }

    public String getName() {
        return "No dependency in POM modules";
    }

    public void stateError(Log log) {
        if (size == 1) {
            log.error("This is a pom module and have a dependency.");
        } else {
            log.error(String.format("This is a pom module and have %d dependencies.", size));
        }
        log.error("By having dependencies in a parent module it often becomes");
        log.error("very hard to maintain the sub-modules dependencies.");
        log.error("It's often not obvious were a dependency is declared,");
        log.error("and you lose the ability to move modules in your file");
        log.error("structure as they have implicit requirements from parent");
        log.error("modules.");
    }
}
