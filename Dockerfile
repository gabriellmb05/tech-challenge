FROM maven:3.9.9-amazoncorretto-21-debian-bookworm AS builder

WORKDIR /app
COPY . /app/

RUN mvn dependency:go-offline && mvn clean install

FROM openjdk:21-slim AS srv_cliente_builder

WORKDIR /opt/app
COPY --from=builder /app/srv-cliente/target/*.jar /srv-cliente.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/srv-cliente.jar"]

FROM openjdk:21-slim AS srv_produto_builder

WORKDIR /opt/app
COPY --from=builder /app/srv-produto/target/*.jar /srv-produto.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/srv-produto.jar"]

FROM openjdk:21-slim AS srv_pagamento_builder

WORKDIR /opt/app
COPY --from=builder /app/srv-pagamento/target/*.jar /srv-pagamento.jar

EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/srv-pagamento.jar"]
