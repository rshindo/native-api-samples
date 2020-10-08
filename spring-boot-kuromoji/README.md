# spring-boot-kuromoji

## Stack

- WebFlux
- Actuator

## Run (JVM)

```
$ mvn spring-boot:run
```

or

```
$ java -jar spring-boot-kuromoji-0.0.1-SNAPSHOT.jar
```

## Build jar

```
$ mvn package
```

## Build OCI image (JVM)

```
$ mvn package spring-boot:build-image
```

## Build OCI image (Native)

```
$ mvn package spring-boot:build-image -P native
```