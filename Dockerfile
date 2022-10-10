FROM openjdk:19-alpine3.15 AS base

WORKDIR /comicworld

COPY ./target/comicworld-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java", "-jar", "/comicworld/comicworld-0.0.1-SNAPSHOT.jar"]

