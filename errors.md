# Maven Build Error Report

## Error 1: Surefire Test Plugin Dependency Error

### Summary of the Issue

When running `.\mvnw.cmd test`, Maven failed during the test execution phase with:
```text
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:3.2.5:test (default-test) on project democompiler: Execution default-test of goal org.apache.maven.plugins:maven-surefire-plugin:3.2.5:test failed: A required class was missing while executing org.apache.maven.plugins:maven-surefire-plugin:3.2.5:test: org/apache/maven/surefire/booter/KeyValueSource
```

### Cause of the Error

The `pom.xml` originally used an older version (`3.2.5`) of the Surefire test plugin. In the local Maven cache (`.m2`), some dependency files were corrupted or incomplete, causing Maven to fail when starting the JUnit test runner. The project source code itself had no errors.

### Solution Applied
Updated the `maven-surefire-plugin` to version `3.5.6` and `junit-jupiter` to version `5.12.2` in `pom.xml`.


---

## Error 2: Java Class Version Mismatch (69.0 vs 65.0)

### Summary of the Issue
When running `.\mvnw.cmd compile exec:java`, the following error occurred:
```text
[ERROR] Failed to execute goal org.codehaus.mojo:exec-maven-plugin:3.2.0:java (default-cli) on project democompiler: An exception occurred while executing the Java class. com/example/compiler/democompiler/DemocompilerApplication has been compiled by a more recent version of the Java Runtime (class file version 69.0), this version of the Java Runtime only recognizes class file versions up to 65.0 -> [Help 1]
```

### Cause of the Error

- `class file version 69.0` corresponds to **Java 25**.
- `class file version 65.0` corresponds to **Java 21**.
The project was configured with `<release>25</release>`, producing Java 25 bytecode. However, the runtime executing Maven was running on Java 21, which cannot run newer Java 25 class files.

### Solution Applied

In `pom.xml`, updated the compiler release target to **Java 21** (LTS):
```xml
<properties>
    <maven.compiler.source>21</maven.compiler.source>
    <maven.compiler.target>21</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.13.0</version>
    <configuration>
        <release>21</release>
    </configuration>
</plugin>
```
Java 21 is a Long-Term Support (LTS) release and is recognized and supported across both Java 21 and Java 25 environments.

---

## Verification Result
Ran `.\mvnw.cmd clean compile exec:java`:
```text
Source Program:
// Sample compiler test
int x = 10;
float y = 2.5;
if (x >= 5) {
    print("x is greater");
} else {
    x = x + 1;
}

Tokens:
[KEYWORD : "int" | line 2]
[IDENTIFIER : "x" | line 2]
[OPERATOR : "=" | line 2]
[NUMBER : "10" | line 2]
[DELIMITER : ";" | line 2]
...
[EOF : "EOF" | line 8]

[INFO] BUILD SUCCESS
```
Both `.\mvnw.cmd test` and `.\mvnw.cmd compile exec:java` now finish with **BUILD SUCCESS**.
Both `.\mvnw.cmd test` and `.\mvnw.cmd compile exec:java` now finish with **BUILD SUCCESS**.
```