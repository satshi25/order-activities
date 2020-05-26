FROM mysql
ENV MYSQL_DATABASE dbtest
COPY ./sql-scripts/ /docker-entrypoint/-initdb.d/


FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} order-activities-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "jar", "/order-activities-0.0.1-SNAPSHOT.jar"]