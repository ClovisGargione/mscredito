FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /eurekaserver
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:17
WORKDIR /eurekaserver
COPY --from=build ./eurekaserver/target/*.jar ./eurekaserver.jar
EXPOSE 8761
ENTRYPOINT java -jar eurekaserver.jar