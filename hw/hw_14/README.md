# Homework 14

## Requirements

- JDK 27
- Maven

## Build all modules

Run this command from the repository root. The Maven reactor builds the logger and math libraries before the demo that depends on them.

```bash
mvn -f hw/pom.xml clean install
```

## Run the demo

From the repository root:

```bash
mvn -f hw/hw_14/pom.xml clean compile exec:java
```

The demo reads `hw/hw_14/config.txt` and writes log files under `hw/hw_14/logs/`. The libraries are installed into the local Maven repository by the build step above.

## Build the demo JAR

```bash
mvn -f hw/hw_14/pom.xml package
```

The demo JAR has no `Main-Class` manifest entry and does not bundle its dependencies. Use the Maven command above to run it; do not use `java -jar`.
