package dk.kyuff.pomlint.rules;

import org.apache.maven.model.Model;
import org.apache.maven.project.MavenProject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmptyDescriptionRuleTest {

    @Mock
    private MavenProject project;
    @Mock
    private Model model;

    private EmptyDescriptionRule rule;

    @Before
    public void setUp() throws Exception {
        when(project.getOriginalModel()).thenReturn(model);
        rule = new EmptyDescriptionRule();
    }

    @Test
    public void valid_description() throws Exception {
        // setup
        when(model.getDescription()).thenReturn("This is a valid description");

        // execute and verify
        assertTrue(rule.valid(project));
    }

    @Test
    public void invalid_null() throws Exception {
        // setup
        when(model.getDescription()).thenReturn(null);

        // execute and verify
        assertFalse(rule.valid(project));
    }

    @Test
    public void invalid_whitespace() throws Exception {
        // setup
        when(model.getDescription()).thenReturn("    \n ");

        // execute and verify
        assertFalse(rule.valid(project));
    }

}