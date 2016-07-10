# pomlint-maven-plugin

This is a Maven module that have the goal to catch common errors in POM files of large Maven projects.

It does this by binding to the validate phase and verifying some simple rules have not been violated.
 
These rules are opinionated and based on experience with large projects.
  
## Usage
  
  Add the following to your root pom file:
  
  
        <build>
            <plugins>
                <plugin>
                    <groupId>dk.kyuff.pomlint</groupId>
                    <artifactId>pomlint-maven-plugin</artifactId>
                    <version>${pomlint.version}</version>
                    <executions>
                        <execution>
                            <id>lint</id>
                            <goals>
                                <goal>lint</goal>
                            </goals>
                        </execution>
                    </executions>
                </plugin>
            </plugins>
        </build>
        
# Rules

Currently `pomlint-maven-plugin` supports four different rules:

1. Empty Description
TODO Descriptions needs to be specified.

1. Inherent Dependency
TODO No inherent dependencies.

1. Mixed Property Names
TODO No mixing property names!

1. Test Scope Out Of Order
TODO Test scopes should be at the end.

1. Module Name
TODO Module names must match the artifactId