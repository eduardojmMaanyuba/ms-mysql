FROM openjdk:17-jdk-alpine

COPY /target/ms-mysql.jar ms-mysql.jar

ENTRYPOINT ["java", "-jar", "ms-mysql.jar"]