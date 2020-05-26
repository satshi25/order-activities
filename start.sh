#!/bin/bash
gradle build -x test
docker build --build-arg JAR_FILE=build/libs/*.jar -t springboot-app .
docker-compose up
docker run -p 9800:9800 springboot-app