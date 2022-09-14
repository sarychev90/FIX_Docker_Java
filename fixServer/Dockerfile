# Build stage
FROM maven:3.8.6-openjdk-18 AS build
COPY . /home
RUN mvn -f /home/pom.xml clean install -pl :fixServer

# Package stage
FROM openjdk:18.0-jdk-slim
ARG JAR_FILE=*.jar
COPY --from=build /home/fixServer/target/${JAR_FILE} /usr/local/lib/fixserver.jar
EXPOSE 9876 9877 9878 9879 9880 9881
CMD ["java","-jar","/usr/local/lib/fixserver.jar"]