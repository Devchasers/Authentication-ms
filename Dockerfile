FROM openjdk:17
EXPOSE 8081
ADD target/authentication-ms.jar authentication-ms.jar
ENTRYPOINT ["java", "-jar", "authentication-ms.jar"]