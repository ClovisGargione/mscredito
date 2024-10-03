FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /cartoes
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:17
WORKDIR /cartoes
COPY --from=build ./cartoes/target/*.jar ./cartoes.jar
ENTRYPOINT java -jar cartoes.jar --spring.profiles.active=docker