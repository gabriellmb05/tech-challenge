FROM openjdk:21-slim

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} tech-challenge-01-1.0-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/tech-challenge-01-1.0-SNAPSHOT.jar"]