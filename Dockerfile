FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
COPY ./sql-scripts/ /docker-entrypoint/-initdb.d/
ENTRYPOINT ["java", "-jar", "app.jar"]