FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /avaliadorcredito
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:17
WORKDIR /avaliadorcredito
COPY --from=build ./avaliadorcredito/target/*.jar ./avaliadorcredito.jar
ENTRYPOINT java -jar avaliadorcredito.jar --spring.profiles.active=docker