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
        
        