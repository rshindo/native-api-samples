# micronaut-kuromoji

## Build native image

```
./gradlew clean assemble
native-image -H:Class=com.github.rshindo.Application -H:Name=application --no-fallback -cp build/libs/*.jar:build/resources:build/layers/application.jar
```

