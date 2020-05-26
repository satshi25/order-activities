#!/bin/bash
gradle build -x test
docker build --build-arg JAR_FILE=build/libs/*.jar -t springboot-app .
docker-compose up