FROM gradle:9.3.0-jdk21 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
EXPOSE $PORT
COPY --from=build /home/gradle/src/build/libs/quizie-all.jar /app/quizie.jar
ENTRYPOINT ["java", "-jar", "/app/quizie.jar"]
