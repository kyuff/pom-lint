# Notes on how to performing a release

First verify things are as expected by doing a

    mvn clean deploy

From this point, the artifacts can be found at:
 
https://oss.sonatype.org/content/repositories/snapshots/dk/kyuff/pomlint/

Next up, set the expected version

    mvn versions:set -DnewVersion=1.2.3

Perform the release with:

    mvn clean deploy -Prelease



