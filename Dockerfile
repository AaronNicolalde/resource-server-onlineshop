FROM openjdk:12-alpine

COPY target/resource-server-0.0.1-SNAPSHOT.jar /resource-server.jar

CMD ["java", "-jar", "/resource-server.jar"]