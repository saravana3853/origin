FROM openjdk:8-jdk-alpine
MAINTAINER saravanaraj
COPY target/origin-1.0-SNAPSHOT.jar origin-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/origin-1.0-SNAPSHOT.jar"]