package dk.kyuff.pomlint.rules;

import dk.kyuff.pomlint.DisableRule;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

public class PropertyNamingRule extends DisableRule {


    public boolean valid(MavenProject project) {
        boolean usedDotted = false;
        boolean usedDashed = false;
        for (String name : project.getProperties().stringPropertyNames()) {
            if (name.contains("-")) {
                usedDashed = true;
            }
            if (name.contains(".")) {
                usedDotted = true;
            }
        }
        return !(usedDashed && usedDotted);
    }

    public String getName() {
        return "Property Naming";
    }

    public void stateError(Log log) {
        log.error("By mixing dotted and dashed naming conventions pom files become");
        log.error("harder to read and maintain. This rule enforces the use of only");
        log.error("one naming convention");
    }
}
