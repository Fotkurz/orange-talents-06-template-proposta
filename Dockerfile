FROM openjdk:11
MAINTAINER guilherme
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
