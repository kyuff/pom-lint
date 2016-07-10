package dk.kyuff.pomlint.rules;

import org.apache.maven.project.MavenProject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ModuleNameRuleTest {

    @Mock
    MavenProject project;

    ModuleNameRule rule;

    @Before
    public void setUp() throws Exception {
        rule = new ModuleNameRule();
    }

    @Test
    public void valid() throws Exception {
        // setup
        when(project.getName()).thenReturn("artifactId");
        when(project.getArtifactId()).thenReturn("artifactId");

        // execute and verify
        assertTrue(rule.valid(project));
    }

    @Test
    public void valid_name_null() throws Exception {
        // setup
        when(project.getName()).thenReturn(null);
        when(project.getArtifactId()).thenReturn("artifactId");

        // execute and verify
        assertTrue(rule.valid(project));
    }

    @Test
    public void valid_name_empty() throws Exception {
        // setup
        when(project.getName()).thenReturn("");
        when(project.getArtifactId()).thenReturn("artifactId");

        // execute and verify
        assertTrue(rule.valid(project));
    }


    @Test
    public void valid_named_contained() throws Exception {
        // setup
        when(project.getName()).thenReturn("Long name - artifactId");
        when(project.getArtifactId()).thenReturn("artifactId");

        // execute and verify
        assertTrue(rule.valid(project));
    }


    @Test
    public void invalid_name() throws Exception {
        // setup
        when(project.getName()).thenReturn("something else");
        when(project.getArtifactId()).thenReturn("artifactId");

        // execute and verify
        assertFalse(rule.valid(project));
    }

}