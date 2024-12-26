# Etapa 1: Buildar a aplicação
FROM maven:3.8.5-openjdk-21-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Criar imagem final
FROM openjdk:21-slim
WORKDIR /app
COPY --from=build /app/target/srv-produto-1.0-SNAPSHOT.jar srv-produto-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/srv-produto-1.0-SNAPSHOT.jar"]