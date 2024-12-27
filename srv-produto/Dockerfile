FROM parent:0.0.1 AS SRV_PRODUTO_BUILD

WORKDIR /app

COPY --from=parent:0.0.1 /app/pom.xml .
COPY --from=parent:0.0.1 /app/srv-produto/pom.xml ./srv-produto/
COPY --from=parent:0.0.1 /app/srv-produto/src ./srv-produto/src/

RUN mvn spotless:apply && mvn -B -e clean install -DskipTests

FROM openjdk:21-slim

WORKDIR /opt/app

COPY --from=SRV_PRODUTO_BUILD /app/srv-produto/target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]