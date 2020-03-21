
# OceanCleaner

OceanCleaner is a Java app to clean the ocean from oil patches.

## Run

Build the the jar file with Maven executing the following command from the root of the project.

```bash
mvn install
```

Run the app with Java.

```bash
java -jar target/ocean-cleaner-0.0.1-SNAPSHOT.jar
```

## API
To use the App send a POST call at:

```bash
http://localhost:8080/clean
```

This an example of the body:

```json
{
  "areaSize" : [5, 5],
  "startingPosition" : [1, 2],
  "oilPatches" : [
    [1, 0],
    [2, 2],
    [2, 3]
  ],
  "navigationInstructions" : "NNESEESWNWW"
}
```

