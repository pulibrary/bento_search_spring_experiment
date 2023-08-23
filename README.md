### Run locally

```
asdf plugin add java
asdf plugin add maven
asdf install
. ~/.asdf/plugins/java/set-java-home.zsh
export MAVEN_HOME=$(asdf where maven)
mvn spring-boot:run
mvn clean spring-boot:run # delete all compiled files and recompile
```

The application will be available in your browser at http://localhost:8080
