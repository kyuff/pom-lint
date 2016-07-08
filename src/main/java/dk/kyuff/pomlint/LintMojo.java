package dk.kyuff.pomlint;

import dk.kyuff.pomlint.rules.DescriptionRule;
import dk.kyuff.pomlint.rules.PomDependencyRule;
import dk.kyuff.pomlint.rules.PropertyNamingRule;
import dk.kyuff.pomlint.rules.TestScopeAtEndRule;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Lint goal verifies the project in the build. If rules are violated the build will fail
 * unless running in dry mode. The rules violated will be described in the Maven error log
 * making it easy for a build server to catch it and display the mistake in the build log.
 */
@Mojo(name = "lint", defaultPhase = LifecyclePhase.VALIDATE, threadSafe = true, aggregator = true)
public class LintMojo extends SuperMojo {

    private static final String SUCCESS = "✓";
    private static final String FAILURE = "X";
    private static final String DISABLED = "-";

    public void execute() throws MojoExecutionException {

        List<Rule> rules = Arrays.asList(
                new DescriptionRule().setDisabled(allowEmptyDescription),
                new PomDependencyRule().setDisabled(allowDependenciesInPomModules),
                new TestScopeAtEndRule().setDisabled(allowTestScopeDependenciesOutOfOrder),
                new PropertyNamingRule().setDisabled(allowMixedPropertyNames)
        );

        List<Rule> failures = new ArrayList<Rule>();

        boolean allValid = true;

        getLog().info("");
        getLog().info(String.format("\t%-45s   %s", "Rule", "Result"));
        getLog().info("\t----------------------------------------------------------------------");
        for (Rule rule : rules) {
            boolean valid = rule.valid(project);
            if (!valid) {
                allValid = false;
                failures.add(rule);
            }
            String status = rule.isDisabled() ? DISABLED : (valid ? SUCCESS : FAILURE);
            getLog().info(String.format("\t%-45s : %s", rule.getName(), status));
        }
        getLog().info("\t----------------------------------------------------------------------");
        getLog().info(String.format("\t%s : Success, %s : Failure, %s : Disabled", SUCCESS, FAILURE, DISABLED));
        getLog().info("\t----------------------------------------------------------------------");

        if (!allValid) {
            for (Rule rule : failures) {
                if (!rule.isDisabled()) {
                    getLog().error(project.getGroupId() + ":" + project.getArtifactId() + ": " + rule.getName());
                    rule.stateError(getLog());
                    getLog().error("");
                }
            }
        }


    }

}
