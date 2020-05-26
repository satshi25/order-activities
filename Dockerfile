FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} order-activities-0.0.1-SNAPSHOT.jar
COPY ./sql-scripts/ /docker-entrypoint/-initdb.d/
ENTRYPOINT ["java", "jar", "/order-activities-0.0.1-SNAPSHOT.jar"]