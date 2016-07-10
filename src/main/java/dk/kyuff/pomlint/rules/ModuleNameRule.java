package dk.kyuff.pomlint.rules;

import dk.kyuff.pomlint.DisableRule;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

public class ModuleNameRule extends DisableRule {

    private String artifactId;
    private String name;

    @Override
    public boolean valid(MavenProject project) {
        artifactId = project.getArtifactId();
        name = project.getName();
        if (name == null || name.trim().isEmpty()) {
            return true;
        }
        return name.contains(artifactId);
    }

    @Override
    public String getName() {
        return "Module Name";
    }

    @Override
    public void stateError(Log log) {
        log.error(String.format("The module name is '%s' which doesn't contain the artifactId '%s'", name, artifactId));
        log.error("It's a good idea to have the module name contain or be the artifactId.");
        log.error("By enforcing this rule, it becomes easier to find the culprit when a single module fails");
        log.error("in a large multi-module build. Often the alternative would be to have to do a text search in");
        log.error("the pom.xml files to find the failing module.");
    }

}
