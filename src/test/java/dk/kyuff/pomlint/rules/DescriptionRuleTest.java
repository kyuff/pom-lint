package dk.kyuff.pomlint.rules;

import org.apache.maven.plugin.testing.stubs.MavenProjectStub;
import org.apache.maven.project.MavenProject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DescriptionRuleTest {

    private MavenProject project;
    private String description;

    private DescriptionRule rule;

    @Before
    public void setUp() throws Exception {
        project = new MavenProjectStub() {
            @Override
            public String getDescription() {
                return description;
            }
        };
        rule = new DescriptionRule();
    }

    @Test
    public void valid_description() throws Exception {
        // setup
        description = "This is a valid description";

        // execute and verify
        assertTrue(rule.valid(project));
    }

    @Test
    public void invalid_null() throws Exception {
        // setup
        description = null;

        // execute and verify
        assertFalse(rule.valid(project));
    }

    @Test
    public void invalid_whitespace() throws Exception {
        // setup
        description = "    \n ";

        // execute and verify
        assertFalse(rule.valid(project));
    }

    @Test
    public void invalid_empty_string() throws Exception {
        // setup
        description = "    \n ";

        // execute and verify
        assertFalse(rule.valid(project));
    }
}