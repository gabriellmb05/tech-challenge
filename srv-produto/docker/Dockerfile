FROM openjdk:21-slim

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} srv-produto-1.0-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/srv-produto-1.0-SNAPSHOT.jar"]