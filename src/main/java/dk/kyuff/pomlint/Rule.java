package dk.kyuff.pomlint;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

public interface Rule {

    boolean valid(MavenProject project);

    String getName();

    void stateError(Log log);

    Rule setDisabled(boolean disabled);

    boolean isDisabled();
}
