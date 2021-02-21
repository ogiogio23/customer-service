FROM openjdk:8
EXPOSE 8080:8080
ADD /target/customer-service-1.0-SNAPSHOT.jar customer-service-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "customer-service-1.0-SNAPSHOT.jar"]