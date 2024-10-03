FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /clientes
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:17
WORKDIR /clientes
COPY --from=build ./clientes/target/*.jar ./clientes.jar
ENTRYPOINT java -jar clientes.jar --spring.profiles.active=docker