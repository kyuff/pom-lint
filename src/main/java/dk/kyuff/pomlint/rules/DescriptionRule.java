package dk.kyuff.pomlint.rules;

import dk.kyuff.pomlint.Rule;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

public class DescriptionRule implements Rule {

    public boolean valid(MavenProject project) {
        return project.getDescription() != null && !project.getDescription().trim().isEmpty();
    }

    public String getName() {
        return "Description present";
    }

    public void stateError(Log log) {
        log.error("Writing a good description helps two times:");
        log.error("1) If the module description is too hard to write, it's probably");
        log.error("   because the modules design is lacking somehow.");
        log.error("   Perhaps it has too much responsibility?");
        log.error("2) When maintaining the module it is often helpful for your future self");
        log.error("   or another developer to have a proper description that tells what ");
        log.error("   this module is all about.");
    }
}
