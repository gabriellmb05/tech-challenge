FROM parent:0.0.1 AS srv_produto_build

WORKDIR /app

COPY --from=parent:0.0.1 /app/pom.xml .
COPY --from=parent:0.0.1 /app/srv-produto/pom.xml ./srv-produto/
COPY --from=parent:0.0.1 /app/srv-produto/src ./srv-produto/src/

RUN mvn spotless:apply && mvn -B -e clean install -DskipTests

FROM openjdk:21-slim

WORKDIR /opt/app

COPY --from=srv_produto_build /app/srv-produto/target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]