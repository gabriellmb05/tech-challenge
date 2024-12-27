FROM maven:3.9.9-amazoncorretto-21-debian-bookworm AS PARENT_BUILD

WORKDIR /app

COPY pom.xml .
COPY hexagono/pom.xml ./hexagono/
COPY srv-produto/pom.xml ./srv-produto/

COPY hexagono/src ./hexagono/src/
COPY srv-produto/src ./srv-produto/src/