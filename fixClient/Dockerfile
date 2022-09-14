# Build stage
FROM maven:3.8.6-openjdk-18 AS build
COPY . /home
RUN mvn -f /home/pom.xml clean install -pl :fixClient 

# Package stage
FROM openjdk:18.0-jdk-slim
ARG JAR_FILE=*.jar
COPY --from=build /home/fixClient/target/${JAR_FILE} /usr/local/lib/fixclient.jar
EXPOSE 8081
CMD ["java","-jar","-Dspring.profiles.active=docker","/usr/local/lib/fixclient.jar"]