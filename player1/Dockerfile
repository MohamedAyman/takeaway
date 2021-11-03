#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Start with a base image containing Java runtime
FROM openjdk:11-jre-slim

COPY --from=build /home/app/target/player1-0.0.1-SNAPSHOT.jar /usr/local/lib/player1-service.jar
# Make port 5001 available to the world outside this container
EXPOSE 5001

# Run the jar file
ENTRYPOINT ["java","-jar","/usr/local/lib/player1-service.jar"]