package dk.kyuff.pomlint;

import org.apache.maven.it.VerificationException;
import org.apache.maven.it.Verifier;
import org.apache.maven.plugin.testing.resources.TestResources;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class LintMojoIT {

    @Rule
    public TestResources resources = new TestResources();

    @Test
    public void testSimpleProject() throws Exception {
        // prepare
        Verifier verifier = prepareProject("simple");

        // execute
        verifier.executeGoal("install");

        // valid
        verifier.verifyErrorFreeLog();

    }

    @Test
    public void testMultiProject() throws Exception {
        // prepare
        Verifier verifier = prepareProject("multi-project");

        // execute
        verifier.executeGoal("install");

        // valid
        verifier.verifyErrorFreeLog();

    }


    private Verifier prepareProject(String projectName) throws IOException, VerificationException {
        File projectCopy = resources.getBasedir(projectName);
        assertNotNull(projectName + " not found", projectCopy);
        return new Verifier(projectCopy.getAbsolutePath());
    }
}
