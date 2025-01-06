FROM maven:3.9.9-amazoncorretto-21-debian-bookworm AS parent_build

WORKDIR /app

COPY pom.xml .

COPY hexagono/pom.xml ./hexagono/
COPY hexagono/src ./hexagono/src/

COPY srv-produto/pom.xml ./srv-produto/
COPY srv-produto/src ./srv-produto/src/

COPY srv-cliente/pom.xml ./srv-cliente/
COPY srv-cliente/src ./srv-cliente/src/