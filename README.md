# javaserver
Java Web Server - To be tested with [cob_spec](https://github.com/8thlight/cob_spec "github.com/8thlight/cob_spec")

## Compile: ##
```
mvn clean install
```

## Run Tests: ##
```
mvn clean test
```

## Start server: ##
```
java -jar target/java-server-1.0-SNAPSHOT.jar
```

## Parameters ##
`-p` Port for server to use. Default: 5000

`-d` Directory the server uses to serve files. Default: Wherever the .jar is located

## To Run With [Cob Spec] (https://github.com/8thlight/cob_spec "github.com/8thlight/cob_spec") ##
Follow the directions in cob_spec's [README] (https://github.com/8thlight/cob_spec "github.com/8thlight/cob_spec")
to install and set up the testing suite.

Make sure to uncomment out and set the PUBLIC_DIR and SERVER_START_COMMAND variables.

PUBLIC_DIR should point to cob_spec's public directory.

SERVER_START_COMMAND should point to your javaserver's target/java-server-1.0-SNAPSHOT.jar

Navigate to the HttpTestSuite and click Suite. Voila!

