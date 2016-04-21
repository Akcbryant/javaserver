# javaserver
Java Web Server - To be tested with [cob_spec](https://github.com/8thlight/cob_spec "github.com/8thlight/cob_spec")

This web server is an exploration into parsing and handling various HTTP requests.
It is meant as an exercise in implementing a non-trivial project using TDD.

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
java -jar target/javaserver-1.0-SNAPSHOT.jar
```

## Parameters ##
`-p` Port to open server on. Default: 5000

`-d` Directory to serve files out of. Default: location of .jar file

## Cob_Spec setup ##
1. Package cob_spec according to its [README](https://github.com/8thlight/cob_spec).
2. Set the PUBLIC_DIR variable to cob_spec's public directory path.
3. Set the SERVER_START_COMMAND variable to the path to your javaserver-1.0-SNAPSHOT.jar
4. Run cob_spec!


