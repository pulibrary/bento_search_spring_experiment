### Run locally

```
asdf plugin add java
asdf plugin add maven
asdf install
. ~/.asdf/plugins/java/set-java-home.zsh
export MAVEN_HOME=$(asdf where maven)
ssh -L 9090:localhost:8983 pulsys@lib-solr-staging5
mvn spring-boot:run
mvn clean spring-boot:run # delete all compiled files and recompile
```

The application will be available in your browser at http://localhost:8080

### Run against Solr production instead

```
ssh -L 9091:localhost:8983 pulsys@lib-solr-prod6
SOLR_URL=http://localhost:9091/solr/catalog-alma-production mvn spring-boot:run
```

### Run tests

```
# run all tests
mvn test

# run only CatalogController tests
mvn -Dtest=CatalogControllerTests test

# Run CatalogController and BentoApplication tests
mvn -Dtest=CatalogControllerTests,BentoApplicationTests test

# Run only the catalogPathReturnsOK test
mvn -Dtest=CatalogControllerTests#catalogPathReturnsOK test
```

In VSCode, if you have the Java extensions installed, you can simply click the
"Play" button near the test to run it.  This is very convenient, especially since it
displays the results inline in the file, and keeps a log of success/failure.

### Lint

`mvn checkstyle:checkstyle`

This adds a report to target/site/checkstyle.html, which you can peruse in the browser.

### SAST tools


This repository uses [semgrep](https://semgrep.dev/)
and [bearer](https://github.com/bearer/bearer) to
perform static security analysis.

To run semgrep locally:

```
brew install semgrep
semgrep --config auto . # run rules from the semgrep community
```

To run bearer locally:

```
brew install bearer/tap/bearer
bearer scan .
```

### Compile a JAR

```
mvn clean package
```
This step will fail if any tests fail.  Otherwise, it will create a .jar file in the target directory.
