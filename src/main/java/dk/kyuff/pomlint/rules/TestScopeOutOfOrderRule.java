package dk.kyuff.pomlint.rules;

import dk.kyuff.pomlint.DisableRule;
import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

import java.util.ArrayList;
import java.util.List;

public class TestScopeOutOfOrderRule extends DisableRule {

    private List<String> outOfOrder;

    public TestScopeOutOfOrderRule() {
        outOfOrder = new ArrayList<String>();
    }

    public boolean valid(MavenProject project) {

        boolean sorted = true;
        boolean startedTest = false;
        for (Dependency dependency : project.getDependencies()) {
            boolean isTestScope = "test".equals(dependency.getScope());
            if (startedTest && !isTestScope) {
                sorted = false;
                outOfOrder.add(dependency.getManagementKey());
            }
            if (isTestScope) {
                startedTest = true;
            }
        }
        return sorted;
    }

    public String getName() {
        return "Test Scope at End";
    }

    public void stateError(Log log) {
        log.error("Below is a list of dependencies that follows something in test scope.");
        log.error("Having test scope at the end helps maintain a better overview of all the");
        log.error("dependencies in the module.");
        for (String gav : outOfOrder) {
            log.error("\t" + gav);
        }
    }
}
