FROM parent:0.0.1 AS srv_cliente_build

WORKDIR /app

RUN mvn spotless:apply && mvn -B -e clean install -DskipTests

FROM openjdk:21-slim

WORKDIR /opt/app

COPY --from=srv_cliente_build /app/srv-cliente/target/*.jar /app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app.jar"]