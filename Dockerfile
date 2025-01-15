FROM maven:3.9.9-amazoncorretto-21-debian-bookworm AS builder

WORKDIR /app
COPY . /app/

RUN mvn dependency:go-offline && mvn clean install

FROM openjdk:21-slim

WORKDIR /opt/app
COPY --from=builder /app/target/*.jar /srv-monolito.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/srv-monolito.jar"]
