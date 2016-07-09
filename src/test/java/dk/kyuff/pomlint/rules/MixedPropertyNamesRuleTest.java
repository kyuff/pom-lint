package dk.kyuff.pomlint.rules;

import org.apache.maven.plugin.testing.stubs.MavenProjectStub;
import org.apache.maven.project.MavenProject;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MixedPropertyNamesRuleTest {

    MavenProject project;
    MixedPropertyNamesRule rule;

    Properties properties;

    @Before
    public void setUp() throws Exception {
        rule = new MixedPropertyNamesRule();
        properties = new Properties();
        project = new MavenProjectStub() {
            @Override
            public Properties getProperties() {
                return properties;
            }
        };
    }

    @Test
    public void valid_empty_list() throws Exception {
        // setup

        // execute and verify
        assertTrue(rule.valid(project));
    }

    @Test
    public void valid_dotted_names() throws Exception {
        // setup
        properties.put("key.no1", "value1");
        properties.put("key.no2", "value2");
        properties.put("key.no3", "value3");

        // execute and verify
        assertTrue(rule.valid(project));
    }


    @Test
    public void valid_dashed_names() throws Exception {
        // setup
        properties.put("key-no1", "value1");
        properties.put("key-no2", "value2");
        properties.put("key-no3", "value3");

        // execute and verify
        assertTrue(rule.valid(project));
    }

    @Test
    public void invalid_mixed_names() throws Exception {
        // setup
        properties.put("key-no1", "value1");
        properties.put("key.no2", "value2");
        properties.put("key-no3", "value3");

        // execute and verify
        assertFalse(rule.valid(project));
    }

}