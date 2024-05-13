FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/etl-0*-SNAPSHOT.jar etl.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","etl.jar"]

