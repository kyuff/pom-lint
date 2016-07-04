package dk.kyuff.pomlint.rules;

import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.testing.stubs.MavenProjectStub;
import org.apache.maven.project.MavenProject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestScopeAtEndRuleTest {

    private MavenProject project;
    private TestScopeAtEndRule rule;
    private List<Dependency> dependencies;

    @Before
    public void setUp() throws Exception {
        project = new MavenProjectStub() {
            @Override
            public List<Dependency> getDependencies() {
                return dependencies;
            }
        };
        rule = new TestScopeAtEndRule();
    }

    @Test
    public void valid_empty() throws Exception {
        // setup
        dependencies = new ArrayList<Dependency>();

        // execute & verify
        assertTrue(rule.valid(project));
    }

    @Test
    public void valid_all_types() throws Exception {
        // setup
        dependencies = createList(
                "javax.ws.rs:javax.ws.rs-api:2.0.1",
                "javax:javaee-api:7.0:provided",
                "junit:junit:4.12:test");

        // execute & verify
        assertTrue(rule.valid(project));
    }

    @Test
    public void invalid_test_start() throws Exception {
        // setup
        dependencies = createList(
                "org.mockito:mockito:5.0:test",
                "javax.ws.rs:javax.ws.rs-api:2.0.1",
                "javax:javaee-api:7.0:provided",
                "junit:junit:4.12:test");

        // execute & verify
        assertFalse(rule.valid(project));
    }

    @Test
    public void invalid_test_middle() throws Exception {
        // setup
        dependencies = createList(
                "javax.ws.rs:javax.ws.rs-api:2.0.1",
                "org.mockito:mockito:5.0:test",
                "junit:junit:4.12:test",
                "javax:javaee-api:7.0:provided"
        );

        // execute & verify
        assertFalse(rule.valid(project));
    }

    private List<Dependency> createList(String... deps) {
        List<Dependency> dependencies = new ArrayList<Dependency>();

        for (String gav : deps) {
            Dependency dependency = new Dependency();
            String[] fields = gav.split(":");
            for (int index = 0; index < fields.length; index++) {
                switch (index) {
                    case 0:
                        dependency.setGroupId(fields[index]);
                        break;
                    case 1:
                        dependency.setArtifactId(fields[index]);
                        break;
                    case 2:
                        dependency.setVersion(fields[index]);
                        break;
                    case 3:
                        dependency.setScope(fields[index]);
                        break;

                }
                if (dependency.getScope() == null) {
                    dependency.setScope("compile");
                }
                dependencies.add(dependency);
            }
        }
        return dependencies;
    }
}