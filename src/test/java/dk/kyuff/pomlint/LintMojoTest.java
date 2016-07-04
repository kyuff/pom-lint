package dk.kyuff.pomlint;

import org.apache.maven.it.Verifier;
import org.apache.maven.plugin.Mojo;
import org.apache.maven.plugin.testing.MojoRule;
import org.apache.maven.plugin.testing.resources.TestResources;
import org.apache.maven.project.MavenProject;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LintMojoTest {
/*
    @Rule
    public MojoRule rule = new MojoRule();

    @Rule
    public TestResources resources = new TestResources();

    @Test
    public void testSimpleProject() throws Exception {

        File projectCopy = resources.getBasedir("simple");

        Mojo mojo = rule.lookupConfiguredMojo(projectCopy, "lint");
        mojo.execute();

    }
    @Test
    public void testMultiProject() throws Exception {

        File projectCopy = resources.getBasedir("multi-project");

        //Mojo mojo = rule.lookupConfiguredMojo(projectCopy, "lint");
        //mojo.execute();

    }

    @Test
    public void testFullExecution() throws Exception {
        File projectCopy = resources.getBasedir("simple");
        Verifier verifier = new Verifier(projectCopy.getAbsolutePath() );

        verifier.executeGoal("install");
        verifier.verifyErrorFreeLog();
    }*/
}