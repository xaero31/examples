FROM openjdk:17

ENV test.property hiFromDocker

VOLUME user-files
WORKDIR /app
COPY ./build/libs/docker-example.jar /app

EXPOSE 8081
CMD ["java", "-jar", "/app/docker-example.jar"]